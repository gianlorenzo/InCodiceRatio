/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

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
