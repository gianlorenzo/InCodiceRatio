/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.JobDaoImpl;
import it.uniroma3.icr.model.Job;

@Service
public class JobFacade {

	@Autowired
	private JobDaoImpl jobDaoImpl;
	
	public void addJob(Job j) {
		jobDaoImpl.insertJob(j);
	}
	
	public Job retrieveJob(long id) {
		return this.jobDaoImpl.findJob(id);
	}
	
	public List<Job> retriveAlljobs() {
		return this.jobDaoImpl.findAll();
	}
	
}
