package it.uniroma3.icr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.StudentDaoImpl;
import it.uniroma3.icr.model.Student;

@Service
public class StudentFacade {
	@Autowired
	private StudentDaoImpl userDaoImpl;
	
	public void addUser(Student user) {
		userDaoImpl.insertUser(user);
	}
	
	public Student retrieveUser(String username) {
		return this.userDaoImpl.findUser(username);
	}
	
	public List<Student> findAll() {
		return this.userDaoImpl.findAll();
	}

}
