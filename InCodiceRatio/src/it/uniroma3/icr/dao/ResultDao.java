/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.dao;

import java.util.List;

import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Task;

public interface ResultDao {
	public void insertResult(Result result);
	
	public Result findResult(long id);
	
	public List<Result> findAll();
	
	public List<Result> findTaskResults(Task task);
	
	public void updateResult(Result result);
	
	public void deleteResult(long id);
	
	public void addImageAdnTaskToResult(Task t,Result r,Job j);
	
}
