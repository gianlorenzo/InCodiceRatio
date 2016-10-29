package it.uniroma3.icr.dao;

import java.util.List;

import it.uniroma3.icr.model.Job;
import it.uniroma3.icr.model.Result;
import it.uniroma3.icr.model.Task;

public interface ResultDao {
	public void insertResult(Result result);
	
	public Result findResult(long id);
	
	public List<Result> findAll();
	
	public List<Result> findTaskResults(Task task);
	
	public void updateResult(Result result);
	
	public void deleteResult(long id);
	
	public void addImageAdnTaskToResult(Task t,Result r,Job j);
	
}
