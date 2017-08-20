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

import it.uniroma3.icr.model.Student;

public interface StudentDao {
	public void insertUser(Student user);
	public Student findUser(String username);
	public Student findUserBySurname(String surname);

	public List<Student> findAll();
	public void updateStudent(Student s);

}
