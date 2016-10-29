package it.uniroma3.icr.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.StudentDao;
import it.uniroma3.icr.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {


	@Autowired
	private SessionFactory sessionFactory;

	public void insertUser(Student user){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

	}

	public Student findUser(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Student u WHERE u.username = :username";
		Query query = session.createQuery(s);
		query.setParameter("username", username);
		Student u = (Student)query.uniqueResult();
		session.close();
		return u;

	}

}
