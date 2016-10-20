package it.uniroma3.icr.dao;

import it.uniroma3.icr.model.Student;

public interface StudentDao {
	public void insertUser(Student user);
	public Student findUser(String username);

}
