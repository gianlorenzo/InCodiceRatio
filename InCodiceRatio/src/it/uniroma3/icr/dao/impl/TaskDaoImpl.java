package it.uniroma3.icr.dao.impl;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> studentsProductivity() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "select student.id, student.name, student.surname, count(*)/40 as numero_task from student, task, result where (student.id=task.student_id and task.id = result.task_id) group by student.id order by numero_task ";
		Query query = session.createSQLQuery(sql);
		List<Object> tasks = query.list();
		session.close();
		return tasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> taskTimes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql= " select to_char(avg(task.enddate - task.startdate), 'HH12:MI:SS') as tempo_medio, to_char(max(task.enddate - task.startdate), 'HH12:MI:SS') as tempo_massimo, to_char(min(task.enddate - task.startdate), 'HH12:MI:SS') as tempo_minimo from task where task.enddate is not null";
		Query query = session.createSQLQuery(sql);
		List<Object> times = query.list();
		System.out.println(times);
		session.close();

		return times;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> majorityVoting() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "select result.image_id, symbol.transcription, count(*) as numero_Yes from symbol, job, task, result where (symbol.id=job.symbol_id and job.id=task.job_id and task.id = result.task_id) and result.answer='Yes' group by result.image_id, symbol.transcription having count(*)>1 order by symbol.transcription";
		Query query = session.createSQLQuery(sql);
		List<Object> voting = query.list();
		session.close();
		return voting;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> symbolAnswers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "select symbol.transcription, count(*) as task_fatti from symbol, job, task, result where (symbol.id=job.symbol_id and job.id=task.job_id and task.id = result.task_id) and result.answer='Yes' group by symbol.id order by symbol.transcription";
		Query query = session.createSQLQuery(sql);
		List<Object> answers = query.list();
		session.close();
		return answers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> voting() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "select result.image_id, symbol.transcription, count(*) as numero_Yes from symbol, job, task, result where (symbol.id=job.symbol_id and job.id=task.job_id and task.id = result.task_id) and result.answer='Yes' group by result.image_id, symbol.transcription order by symbol.transcription";
		Query query = session.createSQLQuery(sql);
		List<Object> voting = query.list();
		session.close();
		return voting;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> symbolsMajorityAnswers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = " select transcription, count(*) from (select result.image_id, symbol.transcription, count(*) as numero_Yes  from symbol, job, task, result where (symbol.id=job.symbol_id and job.id=task.job_id and task.id = result.task_id) and result.answer='Yes' group by result.image_id, symbol.transcription having count(*)>1 order by symbol.transcription) as majorityAnswersVoting group by transcription order by transcription";
		Query query = session.createSQLQuery(sql);
		List<Object> majorityAnswers = query.list();
		session.close();
		return majorityAnswers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> correctStudentsAnswers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "select student.id, student.name, student.surname, count(*) as risposte_corrette from student, task, result, (select result.image_id, symbol.transcription, count(*) as numero_Yes from symbol, job, task, result where (symbol.id=job.symbol_id and job.id=task.job_id and task.id = result.task_id) and result.answer='Yes' group by result.image_id, symbol.transcription having count(*)>1 order by symbol.transcription) as majority where student.id = task.student_id and task.id = result.task_id and majority.image_id=result.image_id group by student.id order by count(*)";
		Query query = session.createSQLQuery(sql);
		List<Object> correct = query.list();
		session.close();
		return correct;
	}

}