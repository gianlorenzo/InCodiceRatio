/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.dao;


import java.util.List;

import it.uniroma3.icr.model.Image;

public interface ImageDao {
	public void insertImage(Image image);

	public Image findImage(long id);

	public List<Image> findAll();

	public List<Image> findImageForTypeAndManuscript(String type, String manuscript, int limit);
	
	public List<Image> findImageForTypeAndWidthAndManuscript(String type, String manuscript,int width, int limit);


	public List<String> findAllManuscript();
	
	public List<String> findAllPages();


	public Object[] countImage();



}
