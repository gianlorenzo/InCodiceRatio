package it.uniroma3.icr.dao;

import java.util.List;

import it.uniroma3.icr.model.Task;

public interface TaskDao {
	
	public void insertTask(Task task);
	public Task findTask(long id);
	public List<Task> findAll();
	List<Task> findTaskByStudent(Long id);
	public void updateEndDate(Task t);

	
}
