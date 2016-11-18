package it.uniroma3.icr.insertImageInDb;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.model.NegativeSample;
import it.uniroma3.icr.model.Sample;
import it.uniroma3.icr.model.Symbol;

@Repository
public class InsertNegativeSampleInDb {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertNegativeSample(NegativeSample negativeSample) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(negativeSample);
		session.getTransaction().commit();
		session.close();
	}

	public Symbol findSymbol(String transcription) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Symbol s WHERE s.transcription = :transcription";
		Query query = session.createQuery(s);
		query.setParameter("transcription", transcription);
		Symbol symbol = (Symbol) query.uniqueResult();
		session.close();
		return symbol;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sample> findAllNegativeSamplesBySymbolId(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM NegativeSample s Where s.symbol.id = :id";
		Query query = session.createQuery(s);
		query.setParameter("id", id);
		List<Sample> samples = query.list();
		return samples;

	}
	
	public void getNegativeSampleImage(String p) throws FileNotFoundException, IOException {
		File[] files = new File(p).listFiles();

		for(int i=0;i<files.length;i++) {

			String symbolType = files[i].getName();

			//prendo la page

			File[] transcriptionsSymbol = files[i].listFiles();
			for(int j = 0;j<transcriptionsSymbol.length;j++) {
				String transcriptionSymbol = transcriptionsSymbol[j].getName();

				File[] manuscripts = transcriptionsSymbol[j].listFiles();
				for(int m=0;m<manuscripts.length;m++) {
					String manuscriptName = manuscripts[m].getName();

					File[] images = manuscripts[m].listFiles();

					for(int g=0;g<images.length;g++) {
						String nameComplete = images[g].getName();


						String pathFile = images[g].getPath();

						String name = FilenameUtils.getBaseName(nameComplete);
						String[] parts = name.split("_");

						int width = Integer.valueOf(parts[0]);
						int x = Integer.valueOf(parts[1]);
						int y = Integer.valueOf(parts[2]);

						BufferedInputStream in = null;

						try {
							BufferedImage f = ImageIO.read(images[g]);

							Symbol s = this.findSymbol(transcriptionSymbol);
							String type = symbolType;
							int height = f.getHeight();
							int xImg = x;
							int yImg = y;
							String manuscript = manuscriptName;
							String path = pathFile.substring(49, pathFile.length());

							NegativeSample negativeSample = new NegativeSample(width,height,xImg,yImg,manuscript,
									type,path);

							negativeSample.setSymbol(s);
							this.insertNegativeSample(negativeSample);

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
