package it.uniroma3.icr.dao.impl;


import java.util.Calendar;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.TaskDao;
import it.uniroma3.icr.model.Student;
import it.uniroma3.icr.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao {


	@Autowired
	private SessionFactory sessionFactory;



	@Override
	public void insertTask(Task task) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(task);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Task findTask(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Task t = (Task) session.get(Task.class, id);
		session.close();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAll() {
		Session session = sessionFactory.openSession();
		String hql ="FROM Task";
		Query query = session.createQuery(hql);
		List<Task> taskList = query.list();
		session.close();
		return taskList;
	}

	private boolean newTaskForStudent(List<Task> tasks, Task task) {
		boolean isNew = true;
		for(Task t : tasks) {
			if(t.getJob().getId().equals(task.getJob().getId()) && 
					t.getBatch() == task.getBatch())
				return false;
		}
		return isNew;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findTaskByStudent(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql ="FROM Task t where t.student.id = :id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		List<Task> studentTasks = query.list();
		session.close();
		System.out.println("Tasks"+studentTasks);
		return studentTasks;
	}


	@SuppressWarnings("unchecked")
	public Task assignTask(Student s, Long id) {

		Task task = null;

		if(id == null) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Student s1 =  (Student) session.get(Student.class, s.getId());
			String hql ="FROM Task WHERE student.id is null ";
			Query query = session.createQuery(hql);
			List<Task> taskList =  query.list();

			List<Task> taskByStudent = this.findTaskByStudent(s.getId());
			for(int i=0;i<taskList.size();i++) {
				if(newTaskForStudent(taskByStudent, taskList.get(i))) {
					task = taskList.get(i);
					task.setStudent(s1);

					Calendar calendar = Calendar.getInstance();
					java.util.Date now = calendar.getTime();
					java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());
					task.setStartDate(date);

					break;
				}
			}
			if(task!=null) {
				s1.addTask(task);
				session.merge(task);
			}
			session.getTransaction().commit();
			session.close();
		} else {
			task = this.findTask(id);
		}


		return task;
	}


	public void updateEndDate(Task t) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();


		java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());

		t.setEndDate(date);

		session.merge(t);
		session.getTransaction().commit();
		session.close();

	}

}