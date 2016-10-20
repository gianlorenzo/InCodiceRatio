package it.uniroma3.icr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.AdminDaoImpl;
import it.uniroma3.icr.model.Administrator;

@Service
public class AdminFacade {

	@Autowired
	private AdminDaoImpl adminDaoImpl;
	
	public void addAdmin(Administrator admin) {
		adminDaoImpl.insertAdmin(admin);
	}
	
	public Administrator retrieveAdmin(String username){
		return this.adminDaoImpl.findAdmin(username);
	}
}
