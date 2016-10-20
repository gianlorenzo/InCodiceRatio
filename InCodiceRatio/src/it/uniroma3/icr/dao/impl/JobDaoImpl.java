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
		System.out.println("Job List:" + empList);
		return empList;
	}

	@Override
	public Job findJob(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Job j = (Job) session.get(Job.class, id);
		
		return j;
	}

	
	
	

	

}
