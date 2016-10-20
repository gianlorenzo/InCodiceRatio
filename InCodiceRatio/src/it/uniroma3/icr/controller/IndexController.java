package it.uniroma3.icr.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.icr.service.impl.ImageFacade;
import it.uniroma3.icr.service.impl.SymbolFacade;


@Controller
public class IndexController {
	
	@Autowired
	private ImageFacade imageFacade;
	
	@Autowired
	private SymbolFacade symbolFacade;
	
	
	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String goToIndex() throws FileNotFoundException, IOException {
		//imageFacade.getListImageProperties();
		//symbolFacade.getSampleImage();
		//symbolFacade.insertSymbolInDb();
		return "index";
	}
}
