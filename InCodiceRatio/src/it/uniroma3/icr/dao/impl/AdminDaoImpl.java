package it.uniroma3.icr.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.AdminDao;
import it.uniroma3.icr.model.Administrator;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void insertAdmin(Administrator administrator) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(administrator);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Administrator findAdmin(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Administrator a WHERE a.username = :username";
		Query query = session.createQuery(s);
		query.setParameter("username", username);
		Administrator a = (Administrator)query.uniqueResult();
		return a;
	}

}
