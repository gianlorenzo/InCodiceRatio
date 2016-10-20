package it.uniroma3.icr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.icr.model.Image;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Sample;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.model.Symbol;
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
	public String task(@ModelAttribute Task task,@ModelAttribute Job job,@ModelAttribute Result result,
			@ModelAttribute Image image,
			@ModelAttribute("taskResults")TaskWrapper taskResults,Model model) {


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String s = auth.getName();
		Student student = studentFacade.retrieveUser(s);
		
		
		task = taskFacade.assignTask(student);

		if(task!=null) {

			List<Sample> samples = symbolFacade.findAllSamplesBySymbolId(task.getJob().getSymbol().getId());

			List<Result> list = resultFacade.findTaskResult(task);
			taskResults.setResultList(list);	

			model.addAttribute("samples", samples);

			model.addAttribute("task", task);
			model.addAttribute("taskResults", taskResults);

			return "users/newTask";
		}
		return "users/goodBye";

	}

	@RequestMapping(value="/secondConsole", method = RequestMethod.POST)
	public String taskRecap(@ModelAttribute("taskResults")TaskWrapper taskResults,
			Model model) {
		List<Result> results = taskResults.getResultList();
		for(Result result : results) {
			Task task = result.getTask();
			taskFacade.updateEndDate(task);
			if(result.getAnswer() == null)
				result.setAnswer("No");
		}
		resultFacade.updateListResult(results);

		return "users/homeStudent";

	}
	
	@RequestMapping(value="/studentTasks")
	public String studentTasks(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Student s = studentFacade.retrieveUser(auth.getName());
    	List<Task> studentTasks = taskFacade.findTaskByStudent(s.getId());
    	
    	model.addAttribute("studentTasks", studentTasks);
		return "users/studentTasks";
		}

}		


