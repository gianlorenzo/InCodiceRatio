package it.uniroma3.icr.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.ImageDaoImpl;
import it.uniroma3.icr.insertImageInDb.InsertImageInDb;
import it.uniroma3.icr.model.Image;


@Service
public class ImageFacade {

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

	public void getListImageProperties() throws FileNotFoundException, IOException {
		insertImageInDb.getListJpegProperties();
	}

	public List<Image> getImagesForTypeAndWidth(String type, int width,String manuscript, String page,  int limit) {
		return this.imageDaoImpl.findImageForTypeAndWidth(type, width,manuscript, page, limit);
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


