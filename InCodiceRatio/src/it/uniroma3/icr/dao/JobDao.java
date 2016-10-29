package it.uniroma3.icr.dao;

import java.util.List;

import it.uniroma3.icr.model.Job;

public interface JobDao {

	public void insertJob(Job job);
	public List<Job> findAll();
	public Job findJob(long id);


}
