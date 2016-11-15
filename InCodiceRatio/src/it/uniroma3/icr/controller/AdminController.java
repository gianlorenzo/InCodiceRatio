package it.uniroma3.icr.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
import it.uniroma3.icr.model.ComparatoreSimboloPerNome;
import it.uniroma3.icr.model.Image;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.service.impl.SymbolFacade;
import it.uniroma3.icr.service.impl.TaskFacade;
import it.uniroma3.icr.view.CorrectStudentsAnswer;
import it.uniroma3.icr.view.MajorityAnswers;
import it.uniroma3.icr.view.MajorityVoting;
import it.uniroma3.icr.view.StudentsProductivity;
import it.uniroma3.icr.view.SymbolsAnswers;
import it.uniroma3.icr.view.TaskTimes;
import it.uniroma3.icr.view.Voting;
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
		List<String> pageImage = imageFacade.findAllPages();

		Map<String,String> manuscripts = new HashMap<String,String>();

		for(String manuscript : manuscriptImage) {
			manuscripts.put(manuscript, manuscript);
		}

		Map<String,String> pages = new HashMap<String,String>();


		for(String page : pageImage) {
			pages.put(page, page);
		}

		model.addAttribute("manuscripts", manuscripts);
		model.addAttribute("pages", pages);


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

		List<Symbol> symbols = symbolFacade.retrieveAllSymbols();
		Collections.sort(symbols, new ComparatoreSimboloPerNome());

		model.addAttribute("symbols", symbols);
		model.addAttribute("images", imageFacade.retrieveAllImages());

		List<String> manuscriptImage = imageFacade.findAllManuscript();
		List<String> pageImage = imageFacade.findAllPages();

		Map<String,String> manuscripts = new HashMap<String,String>();

		for(String manuscript : manuscriptImage) {
			manuscripts.put(manuscript, manuscript);
		}

		Map<String,String> pages = new HashMap<String,String>();

		for(String page : pageImage) {
			pages.put(page, page);
		}

		model.addAttribute("manuscripts", manuscripts);
		model.addAttribute("pages", pages);

		model.addAttribute("job", job);
		model.addAttribute("task", task);

		List<Image> jobImages = new ArrayList<>();

		List<Image> imagesTask = imageFacade.getImagesForTypeAndWidth(job.getSymbol().getType(), job.getSymbol().getWidth(),
				job.getImageManuscript(),job.getImagePage(),job.getNumberOfImages());
		
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

	@RequestMapping(value="insertImage")
	public String insertImages(Model model) throws FileNotFoundException, IOException {
		imageFacade.getListImageProperties();
		return "administration/homeAdmin";
	}

	@RequestMapping(value="insertSymbol")
	public String insertSymbol(Model model) throws FileNotFoundException, IOException {

		symbolFacade.insertSymbolInDb();
		return "administration/homeAdmin";
	}

	@RequestMapping(value="insertSample")
	public String insertSample(Model model) throws FileNotFoundException, IOException {
		symbolFacade.getSampleImage();
		return "administration/homeAdmin";
	}

	@RequestMapping(value="/insertNegativeSample")
	public String insertNegativeSample() throws FileNotFoundException, IOException {
		symbolFacade.getNegativeSampleImage();
		return "administration/homeAdmin";

	}

	@RequestMapping(value="/listJobs") 
	public String jobList(Model model) {

		List<Job> jobs = facadeJob.retriveAlljobs();
		model.addAttribute("jobs", jobs);
		return "administration/listJobs";
	}

	@RequestMapping(value="/resultConsole")
	public String resultConsole() {
		return "administration/resultConsole/resultConsole";
	}

	@RequestMapping(value="/majorityVoting")
	public String majorityVoting(Model model) {
		List<Object> voting = facadeTask.majorityVoting();
		List<MajorityVoting> majority = new ArrayList<>();
		for(Object o : voting) {
			MajorityVoting mj = new MajorityVoting();
			mj.setImageId(((BigInteger)((Object[])o)[0]).intValue());
			mj.setTranscription((String)((Object[])o)[1]);
			mj.setNumberOfYes(((BigInteger)((Object[])o)[2]).intValue());
			majority.add(mj);
		}
		model.addAttribute("majority", majority);

		return "administration/resultConsole/majorityVoting";
	}

	@RequestMapping(value="/symbolsAnswer")
	public String symbolsAnswer(Model model) {
		List<Object> answers = facadeTask.symbolAnswers();
		List<SymbolsAnswers> symbolsAnswers = new ArrayList<>();
		for(Object o : answers) {
			SymbolsAnswers sa = new SymbolsAnswers();
			sa.setTranscription((String)((Object[])o)[0]);
			sa.setCompletedTasks(((BigInteger)((Object[])o)[1]).intValue());
			symbolsAnswers.add(sa);
		}
		model.addAttribute("symbolsAnswers", symbolsAnswers);

		return "administration/resultConsole/symbolsAnswer";
	}

	@RequestMapping(value="/symbolsMajorityAnswer")
	public String symbolsMajorityAnswer(Model model) {
		List<Object> majorityAnswers = facadeTask.symbolsMajorityAnswers();
		List<MajorityAnswers> majority = new ArrayList<>();
		for(Object o : majorityAnswers) {
			MajorityAnswers ma = new MajorityAnswers();
			ma.setTranscription((String)((Object[])o)[0]);
			ma.setCount(((BigInteger)((Object[])o)[1]).intValue());
			majority.add(ma);
		}
		model.addAttribute("majority", majority);

		return "administration/resultConsole/symbolsMajorityAnswer";
	}

	@RequestMapping(value="/tasksTimes")
	public String tasksTimes(Model model) {
		List<Object> times = facadeTask.taskTimes();
		List<TaskTimes> taskTimes = new ArrayList<>();
		for(Object o : times) {
			TaskTimes ts = new TaskTimes();
			ts.setAvgDate(((String)((Object[])o)[0]).toString());
			ts.setMaxDate(((String)((Object[])o)[1]).toString());
			ts.setMinDate(((String)((Object[])o)[2]).toString());
			taskTimes.add(ts);
		}
		model.addAttribute("taskTimes", taskTimes);
		return "administration/resultConsole/tasksTimes";
	}

	@RequestMapping(value="/correctStudentsAnswer")
	public String correctStudentsAnswer(Model model) {
		List<Object> correct = facadeTask.correctStudentsAnswers();
		List<CorrectStudentsAnswer> correctAnswers = new ArrayList<>();
		for(Object o : correct) {
			CorrectStudentsAnswer cs = new CorrectStudentsAnswer();
			cs.setId(((BigInteger)((Object[])o)[0]).intValue());
			cs.setName((String)((Object[])o)[1]);
			cs.setSurname((String)((Object[])o)[2]);
			cs.setCorrectAnswers(((BigInteger)((Object[])o)[3]).intValue());
			correctAnswers.add(cs);
		}
		model.addAttribute("correctAnswers", correctAnswers);
		return "administration/resultConsole/correctStudentsAnswer";
	}

	@RequestMapping(value="/voting")
	public String voting(Model model) {
		List<Object> voting = facadeTask.voting();
		List<Voting> listVoting = new ArrayList<>();
		for(Object o : voting) {
			Voting v = new Voting();
			v.setImageId(((BigInteger)((Object[])o)[0]).intValue());
			v.setTranscription((String)((Object[])o)[1]);
			v.setNumbersOfYes(((BigInteger)((Object[])o)[2]).intValue());
			listVoting.add(v);
		}

		model.addAttribute("listVoting", listVoting);
		return "administration/resultConsole/voting";
	}

	@RequestMapping(value="/studentsProductivity")
	public String studentsProductivity(Model model) throws IllegalArgumentException, IllegalAccessException {
		List<Object> tasks = facadeTask.studentsProductivity();
		List<StudentsProductivity> produttivita = new ArrayList<>();
		for(Object o : tasks) {
			StudentsProductivity ps = new StudentsProductivity();
			ps.setId(((BigInteger)((Object[])o)[0]).longValue());
			ps.setName((String)((Object[])o)[1]);
			ps.setSurname((String)((Object[])o)[2]);
			ps.setNumeroTask(((BigInteger)((Object[])o)[3]).intValue());
			produttivita.add(ps);
		}
		model.addAttribute("produttivita", produttivita);

		return "administration/resultConsole/studentsProductivity";
	}

}

















