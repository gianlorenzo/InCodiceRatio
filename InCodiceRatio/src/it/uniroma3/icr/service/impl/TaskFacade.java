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

import it.uniroma3.icr.dao.impl.TaskDaoImpl;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.model.Task;

@Service
public class TaskFacade {

	@Autowired
	private TaskDaoImpl taskDaoImpl;

	public void addTask(Task t) {
		taskDaoImpl.insertTask(t);
	}

	public Task retrieveTask(long id) {
		return this.taskDaoImpl.findTask(id);
	}

	public List<Task> retrieveAllTask() {
		return this.taskDaoImpl.findAll();
	}

	public List<Task> findTaskByStudent(Long id) {
		return this.taskDaoImpl.findTaskByStudent(id);
	}

	public Task assignTask(Student s, Long id) {
		return this.taskDaoImpl.assignTask(s, id);
	}
	public void updateEndDate(Task t) {
		taskDaoImpl.updateEndDate(t);
	}	

	public List<Object> studentsProductivity() {
		return this.taskDaoImpl.studentsProductivity();
	}
	
	public List<Object> taskTimes() {
		return this.taskDaoImpl.taskTimes();
	}
	
	public List<Object> majorityVoting() {
		return this.taskDaoImpl.majorityVoting();
	}
	
	public List<Object> symbolAnswers() {
		return this.taskDaoImpl.symbolAnswers();
	}
	
	public List<Object> voting() {
		return this.taskDaoImpl.voting();
	}
	
	public List<Object> symbolsMajorityAnswers() {
		return this.taskDaoImpl.symbolsMajorityAnswers();
	}

	public List<Object> correctStudentsAnswers() {
		return this.taskDaoImpl.correctStudentsAnswers();
	}



}