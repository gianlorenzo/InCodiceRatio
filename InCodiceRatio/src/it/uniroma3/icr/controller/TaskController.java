package it.uniroma3.icr.controller;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.icr.model.ComparatorePerData;
import it.uniroma3.icr.model.Image;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Sample;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.model.Task;
import it.uniroma3.icr.model.TaskWrapper;
import it.uniroma3.icr.service.editor.ImageEditor;
import it.uniroma3.icr.service.editor.TaskEditor;
import it.uniroma3.icr.service.impl.ImageFacade;
import it.uniroma3.icr.service.impl.JobFacade;
import it.uniroma3.icr.service.impl.ResultFacade;
import it.uniroma3.icr.service.impl.StudentFacade;
import it.uniroma3.icr.service.impl.SymbolFacade;
import it.uniroma3.icr.service.impl.TaskFacade;



@Controller
public class TaskController {

	private @Autowired ImageEditor imageEditor;

	private @Autowired TaskEditor taskEditor;

	@Autowired
	public SymbolFacade symbolFacade;

	@Autowired
	public JobFacade facadeJob;

	@Autowired
	public ImageFacade imageFacade;

	@Autowired
	public TaskFacade taskFacade;

	@Autowired
	public StudentFacade studentFacade;

	@Autowired ResultFacade resultFacade;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Image.class, this.imageEditor);
		binder.registerCustomEditor(Task.class, this.taskEditor);

	}

	public @ModelAttribute("taskResults")TaskWrapper setupWrapper() {
		return new TaskWrapper();
	}

	@RequestMapping(value="/newTask", method = RequestMethod.GET)
	public String task(@ModelAttribute Task task, @ModelAttribute Job job, @ModelAttribute Result result,
			@ModelAttribute("taskResults")TaskWrapper taskResults, Model model,
			HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String s = auth.getName();
		Student student = studentFacade.retrieveUser(s);
		model.addAttribute("student", student);

		Long taskId = (Long)request.getSession().getAttribute("thisId");
		
		task = taskFacade.assignTask(student, taskId);

		if(task!=null) {

			request.getSession().setAttribute("thisId", task.getId());

			List<Sample> positiveSamples = symbolFacade.findAllSamplesBySymbolId(task.getJob().getSymbol().getId());
			List<Sample> negativeSamples = symbolFacade.findAllNegativeSamplesBySymbolId(task.getJob().getSymbol().getId());

			List<Result> list = resultFacade.findTaskResult(task);
			Collections.shuffle(list);
			taskResults.setResultList(list);	
			
			model.addAttribute("student", student);

			model.addAttribute("positiveSamples", positiveSamples);
			model.addAttribute("negativeSamples", negativeSamples);

			model.addAttribute("task", task);
			model.addAttribute("taskResults", taskResults);
			

			return "users/newTask";
		}
		return "users/goodBye";
	}

	@RequestMapping(value="/secondConsole", method = RequestMethod.POST)
	public String taskRecap(@ModelAttribute("taskResults")TaskWrapper taskResults,
			Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String s = auth.getName();
		Student student = studentFacade.retrieveUser(s);
		model.addAttribute("student", student);
		
		String action = request.getParameter("action");
		String targetUrl = "";

		
		if("Conferma e vai al prossimo task".equals(action)) {
		
		List<Result> results = taskResults.getResultList();
		for(Result result : results) {
			Task task = result.getTask();
			taskFacade.updateEndDate(task);
			if(result.getAnswer() == null)
				result.setAnswer("No");
		}
		resultFacade.updateListResult(results);
		request.getSession().removeAttribute("thisId");
		response.sendRedirect("newTask");

		targetUrl = "users/newTask";
		}
		else if("Conferma e torna alla pagina dello studente".equals(action)) {
			List<Result> results = taskResults.getResultList();
			for(Result result : results) {
				Task task = result.getTask();
				taskFacade.updateEndDate(task);
				if(result.getAnswer() == null)
					result.setAnswer("No");
			}
			resultFacade.updateListResult(results);
			request.getSession().removeAttribute("thisId");

			targetUrl = "users/homeStudent";
			
		}
		
		return targetUrl;
		
	}

	@RequestMapping(value="/studentTasks")
	public String studentTasks(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Student s = studentFacade.retrieveUser(auth.getName());
		List<Task> studentTasks = taskFacade.findTaskByStudent(s.getId());
		Collections.sort(studentTasks, new ComparatorePerData());
		model.addAttribute("studentTasks", studentTasks);
		model.addAttribute("s", s);
		return "users/studentTasks";
	}

}		


