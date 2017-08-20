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

import it.uniroma3.icr.dao.impl.ResultDaoImpl;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Task;

@Service
public class ResultFacade {

	@Autowired
	private ResultDaoImpl resultDaoImpl;
	
	public void addResult(Result r) {
		resultDaoImpl.insertResult(r);
	}
	
	public Result retrieveResult(long id) {
		return this.resultDaoImpl.findResult(id);
	}
	
	public List<Result> retrieveAllResult() {
		return this.resultDaoImpl.findAll();
	}
	
	public void updateResult(Result result) {
		resultDaoImpl.updateResult(result);
	}
	
	public List<Result> findTaskResult(Task task) {
		return this.resultDaoImpl.findTaskResults(task);
	}
	
	public void deleteResult(long id) {
		resultDaoImpl.deleteResult(id);
	}
	
	public void updateListResult(List<Result> results) {
		resultDaoImpl.updateListResult(results);
	}
	
	public void addImageAndTaskToResult(Task t,Result r, Job j) {
		resultDaoImpl.addImageAdnTaskToResult(t, r, j);
	}
	
}
