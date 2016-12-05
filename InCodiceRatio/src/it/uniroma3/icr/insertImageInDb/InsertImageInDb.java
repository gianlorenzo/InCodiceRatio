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

	@Autowired
	private SessionFactory sessionFactory;

	public void insertImage(Image image) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(image);
		session.getTransaction().commit();
		session.close();

	}

	public void getListJpegProperties(String p) throws FileNotFoundException, IOException {

		getListImageProperties(p);
	}

	public void getListImageProperties(String p) throws FileNotFoundException, IOException {


		File[] files = new File(p).listFiles();

		for(int i=0;i<files.length;i++) {

			String manuscriptImage = files[i].getParent();


			String[] parts1 = manuscriptImage.split("/");

			String finalManuscript = parts1[10];


			String namePage = files[i].getName();

			File[] types = files[i].listFiles();
			for(int m=0;m<types.length;m++) {
				String typeName = types[m].getName();
				File[] images = types[m].listFiles();
				for(int n=0;n<images.length;n++) {
					String nameComplete = images[n].getName();
					String pathFile = images[n].getPath();

					String name = FilenameUtils.getBaseName(nameComplete);
					String[] parts = name.split("_");

					int width = Integer.valueOf(parts[0]);
					int x = Integer.valueOf(parts[1]);
					int y = Integer.valueOf(parts[2]);

					BufferedInputStream in = null;

					try {
						BufferedImage b = ImageIO.read(images[n]);

						int height = b.getHeight();
						int xImg = x;
						int yImg = y;
						String page = namePage;
						String type = typeName;
						String path = pathFile.substring(49, pathFile.length());
						String manuscript = finalManuscript;
						Image img = new Image(width,height,type,manuscript,page,xImg,yImg,path);

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
