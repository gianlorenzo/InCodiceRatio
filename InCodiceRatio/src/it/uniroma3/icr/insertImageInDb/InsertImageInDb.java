package it.uniroma3.icr.insertImageInDb;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.model.Image;

@Repository
public class InsertImageInDb {
	
	private static final String path ="C:\\Users\\NandG\\Documents\\img\\sources\\training\\";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertImage(Image image) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(image);
		session.getTransaction().commit();
		session.close();
		
	}
	
public void getListJpegProperties() throws FileNotFoundException, IOException {
		
		getListImageProperties();
	}
	
	public void getListImageProperties() throws FileNotFoundException, IOException {
		File[] files = new File(path).listFiles();
		
		for(int i=0;i<files.length;i++) {
			
			String manuscriptName = files[i].getName();
			
			//prendo la page
			
			File[] pages = files[i].listFiles();
			for(int j = 0;j<pages.length;j++) {
				String pageName = pages[j].getName();
				
				File[] types = pages[j].listFiles();
				for(int m=0;m<types.length;m++) {
					String typeName = types[m].getName();
					
					File[] images = types[m].listFiles();
					
					for(int g=0;g<images.length;g++) {
						String nameComplete = images[g].getName();
						String pathFile = images[g].getPath();
						
						String newPath = pathFile.replace(images[g].separator, "/");
						
						String name = FilenameUtils.getBaseName(nameComplete);
						String[] parts = name.split("_");
						
						int x = Integer.valueOf(parts[0]);
						int y = Integer.valueOf(parts[1]);
						
						BufferedInputStream in = null;
						
						try {
							BufferedImage b = ImageIO.read(images[g]);
							
							int width = b.getWidth();
							int height = b.getHeight();
							int xImg = x;
							int yImg = y;
							String page = pageName;
							String manuscript = manuscriptName;
							String type = typeName;
							String path = newPath.substring(24, newPath.length());
							
							Image img = new Image(width,height,type,page,
									manuscript,xImg,yImg,path);;
									
							this.insertImage(img);
						}
						finally {
				            if (in != null) {
				                try {
				                	
				                    in.close();
				                }
				                catch (IOException e) {
				                	e.printStackTrace();
				                }
				           }
				        }
					}
				}
				
			}
			
		}
			
}


}
