/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

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
		session.close();
		return a;
	}

}
