package it.uniroma3.icr.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	private final static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String goToIndex(Model model) {
		try {
			return "index";
		} catch(Exception e) {
			logger.error("FATAL EXCEPTION", e);
			model.addAttribute("e", e);
			model.addAttribute("error", e.toString());
			return "error";
		}
	}
}
