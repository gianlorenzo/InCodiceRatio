package it.uniroma3.icr.controller;

import java.util.HashMap;
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

import it.uniroma3.icr.model.Student;

import it.uniroma3.icr.service.impl.StudentFacade;

@Controller
public class UserController {

	@Autowired
	private StudentFacade userFacade;

	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registrazione(@ModelAttribute Student student, Model model) {
		
			Map<String,String> schoolGroups = new HashMap<String,String>();
			schoolGroups.put("3", "3");
			schoolGroups.put("4", "4");
			schoolGroups.put("5", "5");
			model.addAttribute("schoolGroups", schoolGroups);

			return "registration";
	}

	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String confirmUser(@ModelAttribute Student student, Model model, @Validated Student p, BindingResult bindingResult) {

			Map<String,String> schoolGroups = new HashMap<String,String>();
			schoolGroups.put("3", "3");
			schoolGroups.put("4", "4");
			schoolGroups.put("5", "5");
			model.addAttribute("schoolGroups", schoolGroups);

			Student u = userFacade.retrieveUser(student.getUsername());
			if(bindingResult.hasErrors()) {
				return "registration";
			}
			if(u!=null) {
				model.addAttribute("usernameError","Username già esistente");
				return "registration";
			}
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String passwordEncode = passwordEncoder.encode(student.getPassword());
			student.setPassword(passwordEncode);
			model.addAttribute("student", student);

			userFacade.addUser(student);
			return "login";


	}

	@RequestMapping(value="/homeStudent")
	public String toHomeStudent() {
		return "users/homeStudent";
	}

}
