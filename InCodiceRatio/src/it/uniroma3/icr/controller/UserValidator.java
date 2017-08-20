/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.controller;

import org.springframework.validation.*;

import it.uniroma3.icr.model.Student;

public class UserValidator implements Validator {

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "Non deve essere vuoto");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "Non deve essere vuoto");

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

}



