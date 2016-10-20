package it.uniroma3.icr.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.ResultDao;
import it.uniroma3.icr.model.Image;
import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Task;

@Repository
public class ResultDaoImpl implements ResultDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertResult(Result result) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(result);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Result findResult(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Result r = (Result) session.get(Result.class, id);
		return r;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> findAll() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Result";
		Query query = session.createQuery(hql);
		List<Result> empList = query.list();
		return empList;
	}

	@Override
	public void updateResult(Result result) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(result);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void updateListResult(List<Result> results) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(Result r : results)
			session.merge(r);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> findTaskResults(Task task) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Result> results =  (List<Result>) session.createQuery("select r from Result r where r.task.id="+task.getId()).list();
		session.getTransaction().commit();
		System.out.println(results);
		return results;
	}

	@Override
	public void deleteResult(long id) {
		Session session = sessionFactory.openSession();
		String hql ="DELETE from Result r where r.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void addImageAdnTaskToResult(Task t, Result r, Job j) {
		for(Image i : j.getImages()) {
			r = new Result();
			r.setImage(i);
			r.setTask(t);
		}
	}

	
	

}