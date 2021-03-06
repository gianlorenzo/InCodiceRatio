/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import it.uniroma3.icr.model.Administrator;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.service.impl.StudentFacade;
import it.uniroma3.icr.service.impl.TaskFacade;

@Controller
public class LoginController  {

	@Autowired
	public TaskFacade taskFacade;

	@Autowired
	public StudentFacade studentFacade;

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
		return "login";
	}

	@RequestMapping(value="/admin**", method = RequestMethod.GET)
	public String loginAdmin() {
		return "administration/homeAdmin";
	}

	@RequestMapping(value="/utente**", method = RequestMethod.GET)
	public String loginUser() {
		return "users/Task";
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login (ModelMap model) {
		model.addAttribute("user", new Student());
		model.addAttribute("admin", new Administrator());
		return "login";

	}

	@RequestMapping(value="/role", method = RequestMethod.GET)
	public String loginRole(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		Student student = studentFacade.retrieveUser(auth.getName());
		String targetUrl = "";

		if(role.contains("ROLE_USER")) {
			model.addAttribute("student", student);
			targetUrl = "/users/homeStudent";
		} else if(role.contains("ROLE_ADMIN")) {
			targetUrl = "/administration/homeAdmin";
		}

		return targetUrl;
	}
}











