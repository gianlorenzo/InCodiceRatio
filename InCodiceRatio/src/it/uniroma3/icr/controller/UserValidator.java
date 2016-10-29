package it.uniroma3.icr.controller;

import org.springframework.validation.*;

import it.uniroma3.icr.model.Student;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "Non deve essere vuoto");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "Non deve essere vuoto");

	}

}
