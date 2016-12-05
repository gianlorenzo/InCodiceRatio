package it.uniroma3.icr.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.ImageDaoImpl;
import it.uniroma3.icr.insertImageInDb.GetManuscript;
import it.uniroma3.icr.insertImageInDb.InsertImageInDb;
import it.uniroma3.icr.model.Image;


@Service
public class ImageFacade {
	
	@Autowired
	private GetManuscript getManuscript;

	@Autowired
	private ImageDaoImpl imageDaoImpl;

	@Autowired
	private InsertImageInDb insertImageInDb;

	public void addImage(Image i) {
		imageDaoImpl.insertImage(i);
	}

	public Image retrieveImage(long id) {
		return this.imageDaoImpl.findImage(id);
	}

	public List<Image> retrieveAllImages() {
		return this.imageDaoImpl.findAll();
	}

	public void getListImageProperties(String p) throws FileNotFoundException, IOException {
		insertImageInDb.getListJpegProperties(p);
	}

	public List<Image> getImagesForTypeAndManuscript(String type,String manuscript, int limit) {
		return this.imageDaoImpl.findImageForTypeAndManuscript(type,manuscript, limit);
	}
	
	public List<Image> findImageForTypeAndWidthAndManuscript(String type, String manuscript, int width, int limit) {
		return this.imageDaoImpl.findImageForTypeAndWidthAndManuscript(type, manuscript, width, limit);
	}

	
	public List<String> getManuscript() throws FileNotFoundException, IOException {
		return this.getManuscript.getManuscript();
	}
	
	public String getPath() throws FileNotFoundException, IOException {
		return this.getManuscript.getPath();
	}



	public List<String> findAllManuscript() {
		return this.imageDaoImpl.findAllManuscript();
	}
	
	public List<String> findAllPages() {
		return this.imageDaoImpl.findAllPages();
	}
	
	public Object[] countImage() {
		return this.countImage();
	}

}


