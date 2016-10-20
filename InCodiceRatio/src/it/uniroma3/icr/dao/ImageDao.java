package it.uniroma3.icr.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import it.uniroma3.icr.model.Image;

public interface ImageDao {
	public void insertImage(Image image);
	
	public Image findImage(long id);
	
	public List<Image> findAll();
	
	public List<Image> findImageForTypeAndWidth(String type, int width);
	
	public List<String> findAllManuscript();
		 

}
