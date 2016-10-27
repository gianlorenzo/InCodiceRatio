package it.uniroma3.icr.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.icr.model.Symbol;
import it.uniroma3.icr.model.Task;
import it.uniroma3.icr.model.Administrator;
import it.uniroma3.icr.model.Image;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.service.impl.SymbolFacade;
import it.uniroma3.icr.service.impl.TaskFacade;
import it.uniroma3.icr.service.editor.SymbolEditor;
import it.uniroma3.icr.service.impl.AdminFacade;
import it.uniroma3.icr.service.impl.ImageFacade;
import it.uniroma3.icr.service.impl.JobFacade;
import it.uniroma3.icr.service.impl.ResultFacade;
import it.uniroma3.icr.service.impl.StudentFacade;

@Controller
public class AdminController {

	private final static Logger logger = Logger.getLogger(AdminController.class);

	private @Autowired SymbolEditor symbolEditor;

	@Autowired
	private AdminFacade adminFacade;

	@Autowired
	private StudentFacade studentFacade;

	@Autowired
	private JobFacade facadeJob;

	@Autowired
	private TaskFacade facadeTask;

	@Autowired
	private ResultFacade facadeResult;

	@Autowired
	private SymbolFacade symbolFacade;;

	@Autowired
	private ImageFacade imageFacade;

	@Qualifier("adminValidator")
	private Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Symbol.class, this.symbolEditor);
		binder.setValidator(validator);
	}

	@RequestMapping(value="/insertJob")
	private String newJob(@ModelAttribute Job job,@ModelAttribute Task task, Model model) {
		try {
			model.addAttribute("symbols", symbolFacade.retrieveAllSymbols());
			model.addAttribute("images", imageFacade.retrieveAllImages());

			List<String> manuscriptImage = imageFacade.findAllManuscript();

			Map<String,String> manuscripts = new HashMap<String,String>();

			for(String manuscript : manuscriptImage) {
				manuscripts.put(manuscript, manuscript);
			}


			model.addAttribute("manuscripts", manuscripts);

			model.addAttribute("job", job);
			model.addAttribute("task", task);
			return"administration/insertJob";
		} catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value="/homeAdmin")
	public String toHomeAdmin() {
		return"administration/homeAdmin";
	}

	@RequestMapping(value="/addJob", method = RequestMethod.POST)
	public String confirmJob(@ModelAttribute Job job,@ModelAttribute Task task,@ModelAttribute Image image,@ModelAttribute Result result, Model model) {

		try {
			model.addAttribute("symbols", symbolFacade.retrieveAllSymbols());
			model.addAttribute("images", imageFacade.retrieveAllImages());

			List<String> manuscriptImage = imageFacade.findAllManuscript();

			Map<String,String> manuscripts = new HashMap<String,String>();

			for(String manuscript : manuscriptImage) {
				manuscripts.put(manuscript, manuscript);
			}


			model.addAttribute("manuscripts", manuscripts);

			model.addAttribute("job", job);
			model.addAttribute("task", task);

			List<Image> jobImages = new ArrayList<>();

			List<Image> imagesTask = imageFacade.getImagesForTypeAndWidth(job.getSymbol().getType(), job.getSymbol().getWidth(),
					job.getNumberOfImages());

			if(job.getNumberOfImages()%job.getTaskSize() == 0) {

				for(int y=0;y<imagesTask.size();y++) {
					image = imagesTask.get(y);
					jobImages.add(image);
				}


				job.setImages(jobImages);
				facadeJob.addJob(job);

				for(int i = 0; i<job.getNumberOfStudents();i++) {
					int batchNumber = 0;

					for(int r=0;r<job.getImages().size();r++) {

						if ((r % job.getTaskSize())==0) {
							task = new Task();
							task.setBatch(batchNumber);
							task.setJob(job);
							facadeTask.addTask(task);
							batchNumber++;
						}

						Image j = job.getImages().get(r);
						result = new Result();
						result.setImage(j);
						result.setTask(task);
						facadeResult.addResult(result);

					}
				}

				return "administration/jobRecap";
			}
			return "administration/insertJob";
		}catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";

		}
	}

	@RequestMapping(value="/registrationAdmin", method=RequestMethod.GET)
	public String registration(@ModelAttribute Administrator administrator, Model model) {
		return "registrationAdmin";
	}

	@RequestMapping(value="/addAdmin", method = RequestMethod.POST)
	public String confirmAdmin(@ModelAttribute Administrator administrator,
			@Validated Administrator a,BindingResult bindingResult,Model model) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordEncode = passwordEncoder.encode(administrator.getPassword());
		administrator.setPassword(passwordEncode);
		model.addAttribute("administrator", administrator);

		adminFacade.addAdmin(administrator);
		return "login";

	}

	@RequestMapping(value="/insertImage")
	public String insertImages(Model model) {
		try{
			imageFacade.getListImageProperties();
			return "administration/homeAdmin";
		}catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}

	}

	@RequestMapping(value="/insertSymbol")
	public String insertSymbol(Model model) {
		try {
			symbolFacade.insertSymbolInDb();
			return "administration/homeAdmin";
		}catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value="/insertSample")
	public String insertSample(Model model) {
		try {
			symbolFacade.getSampleImage();
			return "administration/homeAdmin";
		}catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}

	}

	@RequestMapping(value="/listJobs") 
	public String jobList(Model model) {
		try {
			List<Job> jobs = facadeJob.retriveAlljobs();
			model.addAttribute("jobs", jobs);
			return "administration/listJobs";
		}catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}


	}

	@RequestMapping(value="/resultConsole")
	public String resultConsole() {
		return "administration/resultConsole";
	}

	@RequestMapping(value="/studentsProductivity")
	public String studentsProductitivty(Model model) {
		List<Student> studentsList = studentFacade.findAll();
		model.addAttribute("studentsList", studentsList);

		return "administration/studentsProductivity";
	}

	@RequestMapping(value="/tasksTime") 
	public String tasksTime(Model model) {
		
		double midHour = facadeTask.midTimeHour();
		double midMinute = facadeTask.midTimeMinute();
		double midSecond = facadeTask.midTimeSecond();
		
		int maxHour = facadeTask.maxTimeHour();
		int maxMinute = facadeTask.maxTimeMinute();
		int maxSecond = facadeTask.maxTimeSecond();
		
		int minHour = facadeTask.minTimeHour();
		int minMinute = facadeTask.minTimeMinute();
		int minSecond = facadeTask.minTimeSecond();
		
		model.addAttribute("midHour", midHour);
		model.addAttribute("midMinute", midMinute);
		model.addAttribute("midSecond", midSecond);
		
		model.addAttribute("maxHour", maxHour);
		model.addAttribute("maxMinute", maxMinute);
		model.addAttribute("maxSecond", maxSecond);
		
		model.addAttribute("minHour", minHour);
		model.addAttribute("minMinute", minMinute);
		model.addAttribute("minSecond", minSecond);

		return "administration/tasksTime";
	}

}

















