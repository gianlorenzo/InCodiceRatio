package it.uniroma3.icr.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import it.uniroma3.icr.service.impl.SymbolFacade;
import it.uniroma3.icr.service.impl.TaskFacade;
import it.uniroma3.icr.service.editor.SymbolEditor;
import it.uniroma3.icr.service.impl.AdminFacade;
import it.uniroma3.icr.service.impl.ImageFacade;
import it.uniroma3.icr.service.impl.JobFacade;
import it.uniroma3.icr.service.impl.ResultFacade;

@Controller
public class AdminController {

	private @Autowired SymbolEditor symbolEditor;

	@Autowired
	private AdminFacade adminFacade;

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
	}

	@RequestMapping(value="/homeAdmin")
	public String toHomeAdmin() {
		return"administration/homeAdmin";
	}

	@RequestMapping(value="/addJob", method = RequestMethod.POST)
	public String confirmJob(@ModelAttribute Job job,@ModelAttribute Task task,@ModelAttribute Image image,@ModelAttribute Result result, Model model) {
		
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

		int perc1 = (job.getNumberOfImages()*job.getPercentageType1())/100;
		int perc2 = (job.getNumberOfImages()*job.getPercentageType2())/100;
		int perc3 = (job.getNumberOfImages()*job.getPercentageType3())/100;

		List<Image> imgsType1 = imageFacade.getImagesForType("t1");
		List<Image> imgsType2 = imageFacade.getImagesForType("t2");
		List<Image> imgsType3 = imageFacade.getImagesForType("t3");
		
		if(job.getNumberOfImages()%job.getTaskSize() == 0) {
		//Inserisco le Immagini secondo le percentuali di tipo 1

		for(int k=0;k<job.getNumberOfImages() && k<perc1;k++) {
			image = imgsType1.get(k);
			jobImages.add(image);
		}
			

		//Inserisco le Immagini secondo le percentuali di tipo 2

		for(int h=0;h<job.getNumberOfImages() && h<perc2;h++) {
			image = imgsType2.get(h);
			jobImages.add(image);
		}

		//Inserisco le Immagini secondo le percentuali di tipo 3

		for(int n=0; n<job.getNumberOfImages() && n<perc3;n++) {
			image = imgsType3.get(n);
			jobImages.add(image);
		}

		job.setImages(jobImages);
		facadeJob.addJob(job);

		int numberTask = job.getNumberOfImages()/job.getTaskSize();

		for(int i = 0; i<job.getNumberOfStudents();i++) {
			for(int a=0;a<numberTask;a++) {
				task = new Task();
				task.setBatch(i);
				task.setJob(job);
				facadeTask.addTask(task);

				for(int r=0;r<job.getImages().size() && r<job.getTaskSize();r++) 
					{
					Image j = job.getImages().get(r);
					result = new Result();
					result.setImage(j);
					result.setTask(task);
					facadeResult.addResult(result);
					
				}
			}
		}
	

		return "administration/jobRecap";
		}
		return "administration/insertJob";
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
		adminFacade.addAdmin(administrator);
		return "login";

	}

	@RequestMapping(value="/confirmAdmin", method = RequestMethod.POST)
	public String addAdmin(@ModelAttribute Administrator administrator, 
			@Validated Administrator a,BindingResult bindingResult,Model model){

		Administrator p = adminFacade.retrieveAdmin(administrator.getUsername());
		if(bindingResult.hasErrors()) {
			return "registrationAdmin";
		}
		if(p!=null) {
			model.addAttribute("usernameError", "Username Already Exists");
			return "registrationAdmin";
		}

		model.addAttribute("administrator", administrator);
		return "registrationAdmin";
	}
	
	


}










