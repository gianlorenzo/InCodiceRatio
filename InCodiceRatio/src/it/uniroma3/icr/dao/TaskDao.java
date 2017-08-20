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

import it.uniroma3.icr.model.Task;

public interface TaskDao {
	
	public void insertTask(Task task);
	public Task findTask(long id);
	public List<Task> findAll();
	List<Task> findTaskByStudent(Long id);
	public void updateEndDate(Task t);
	public List<Object> studentsProductivity();
	public List<Object> taskTimes();
	public List<Object> majorityVoting();
	public List<Object> symbolAnswers();
	public List<Object> voting();
	public List<Object> symbolsMajorityAnswers();
	public List<Object> correctStudentsAnswers();

}
