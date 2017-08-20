/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.JobDao;
import it.uniroma3.icr.model.Job;

@Repository
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void insertJob(Job job) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(job);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAll() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Job";
		Query query = session.createQuery(hql);
		List<Job> empList = query.list();
		session.close();
		return empList;
	}

	@Override
	public Job findJob(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Job j = (Job) session.get(Job.class, id);
		session.close();

		return j;
	}

}
