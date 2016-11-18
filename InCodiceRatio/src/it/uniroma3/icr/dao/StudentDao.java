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
