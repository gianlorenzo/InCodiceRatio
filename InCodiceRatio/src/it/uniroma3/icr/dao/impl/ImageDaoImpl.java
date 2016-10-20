package it.uniroma3.icr.dao.impl;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.ImageDao;
import it.uniroma3.icr.insertImageInDb.InsertImageInDb;
import it.uniroma3.icr.model.Image;

@Repository
public class ImageDaoImpl implements ImageDao {
	
	public InsertImageInDb insertImageInDb;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertImage(Image image) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(image);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Image findImage(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Image i = (Image) session.get(Image.class, id);
		
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findAll() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Image";
		Query query = session.createQuery(hql);
		List<Image> empList = query.list();
		System.out.println("Image List:"+empList);
		session.close();
		return empList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findImageForTypeAndWidth(String type,int width) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s ="FROM Image i WHERE i.type = :type and i.width = :width ORDER BY RANDOM()";
		Query query = session.createQuery(s);
		query.setParameter("type", type);
		query.setParameter("width", width);
		List<Image> images = query.list();
		session.close();
		return images;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllManuscript() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "SELECT distinct manuscript FROM Image";
		Query query = session.createQuery(s);
		List<String> manuscripts = query.list();
		session.close();
		return manuscripts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> countImage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "select count (*), type,width from image group by type,width";
		Query query = session.createQuery(s);
		List<Image> images = query.list();
		session.close();
		System.out.println("Image List:"+images);

		
		return images;
	}
	
	
	
	
	


	


}


	
	
	

	
	


