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
		session.getTransaction().commit();
		session.close();
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findAll() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Image";
		Query query = session.createQuery(hql);
		List<Image> empList = query.list();
		session.close();
		return empList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findImageForTypeAndManuscript(String type,String manuscript, int limit) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Image i WHERE i.type = :type and i.manuscript = :manuscript ORDER BY RANDOM()";
		
		Query query = session.createQuery(s);
		query.setParameter("type", type);
		query.setParameter("manuscript", manuscript);
		List<Image> images = query.setMaxResults(limit).list();
		
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
	public Object[] countImage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "select count (*), type,width from image group by type,width";
		Query query = session.createQuery(s);
		List<Image> images = query.list();
		Object[] objectList = images.toArray();
		session.close(); 

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllPages() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "SELECT distinct page FROM Image";
		Query query = session.createQuery(s);
		List<String> pages = query.list();
		session.close();
		return pages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findImageForTypeAndWidthAndManuscript(String type, String manuscript, int width, int limit) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Image i WHERE i.type = :type and i.width = :width and i.manuscript = :manuscript ORDER BY RANDOM()";
		
		Query query = session.createQuery(s);
		query.setParameter("type", type);
		query.setParameter("width", width);
		query.setParameter("manuscript", manuscript);
		
		List<Image> images = query.setMaxResults(limit).list();
		
		session.close();
		return images;	}

}










