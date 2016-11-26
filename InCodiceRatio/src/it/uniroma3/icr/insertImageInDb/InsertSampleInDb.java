package it.uniroma3.icr.insertImageInDb;



import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
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
public class InsertSampleInDb {


	@Autowired
	private SessionFactory sessionFactory;

	public void insertSample(Sample sample) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(sample);
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
	public List<Sample> findAllSamplesBySymbolId(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Sample s Where s.symbol.id = :id";
		Query query = session.createQuery(s);
		query.setParameter("id", id);
		List<Sample> samples = query.list();
		System.out.println("List Samples"+samples);
		return samples;

	}

	public void getSampleImage(String p) throws FileNotFoundException, IOException {
		File[] files = new File(p).listFiles();

		for(int i=0;i<files.length;i++) {
			String manuscriptSymbol = files[i].getParent();
			String newManuscript = manuscriptSymbol.replace(File.separator,"/");
			


			String[] parts1 = newManuscript.split("/");
			String finalManuscript = parts1[11];

			String typeSymbol = files[i].getName();

			File[] transcriptionsSymbol = files[i].listFiles();
			for(int j=0;j<transcriptionsSymbol.length;j++) {
				String transcriptionSymbol = transcriptionsSymbol[j].getName();
				File[] images = transcriptionsSymbol[j].listFiles();

				for(int m=0;m<images.length;m++) {
					String nameComplete = images[m].getName();

					String pathFile = images[m].getPath();
					String newPath = pathFile.replace(File.separator, "/");



					String name = FilenameUtils.getBaseName(nameComplete);
					String parts[] = name.split("_");

					int width = Integer.valueOf(parts[0]);
					int x = Integer.valueOf(parts[1]);
					int y = Integer.valueOf(parts[2]);

					BufferedInputStream in = null;

					try {
						BufferedImage f = ImageIO.read(images[m]);

						Symbol s = this.findSymbol(transcriptionSymbol, finalManuscript);

						int height = f.getHeight();
						int xImg = x;
						int yImg = y;
						String path = newPath.substring(77, newPath.length());

						String type = typeSymbol;
						String manuscript = finalManuscript;

						Sample sample = new Sample(width,height,xImg,yImg,manuscript,
								type,path);

						sample.setSymbol(s);
						this.insertSample(sample);


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








