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

	public Symbol findSymbol(String transcription,String manuscript) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Symbol s WHERE s.transcription = :transcription and s.manuscript = :manuscript";
		Query query = session.createQuery(s);
		query.setParameter("transcription", transcription);
		query.setParameter("manuscript", manuscript);
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
			String manuscriptSymbol = files[i].getParent();

			String[] parts1 = manuscriptSymbol.split("/");
			String finalManuscript = parts1[10];

			String typeSymbol = files[i].getName();

			File[] transcriptionsSymbol = files[i].listFiles();
			for(int j=0;j<transcriptionsSymbol.length;j++) {
				String transcriptionSymbol = transcriptionsSymbol[j].getName();
				File[] images = transcriptionsSymbol[j].listFiles();
				for(int m=0;m<images.length;m++) {

					String nameComplete = images[m].getName();

					String pathFile = images[m].getPath();


					String name = FilenameUtils.getBaseName(nameComplete);
					String parts[] = name.split("_");

					int width = Integer.valueOf(parts[0]);
					int x = Integer.valueOf(parts[1]);
					int y = Integer.valueOf(parts[2]);

					BufferedInputStream in = null;

					try {
						BufferedImage f = ImageIO.read(images[m]);

						Symbol s = this.findSymbol(transcriptionSymbol,finalManuscript);

						int height = f.getHeight();
						int xImg = x;
						int yImg = y;
						String path = pathFile.substring(49, pathFile.length());

						String type = typeSymbol;
						String manuscript = finalManuscript;

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



