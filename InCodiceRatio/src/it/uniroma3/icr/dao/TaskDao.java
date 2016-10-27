package it.uniroma3.icr.dao;

import java.sql.Timestamp;
import java.util.List;

import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.model.Task;

public interface TaskDao {
	
	public void insertTask(Task task);
	public Task findTask(long id);
	public List<Task> findAll();
	List<Task> findTaskByStudent(Long id);
	public void updateEndDate(Task t);
	public double midTimeMinute();
	public double midTimeHour();
	public double midTimeSecond();
	public int maxTimeMinute();
	public int maxTimeHour();
	public int maxTimeSecond();
	public int minTimeMinute();
	public int minTimeHour();
	public int minTimeSecond();

	
}
