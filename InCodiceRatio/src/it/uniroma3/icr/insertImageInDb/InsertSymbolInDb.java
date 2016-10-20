package it.uniroma3.icr.insertImageInDb;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.model.Symbol;

@Repository
public class InsertSymbolInDb {
	private static final String path ="C:\\Users\\NandG\\Documents\\img\\sources\\samples\\";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertSymbol(Symbol symbol) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(symbol);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public void insertSymbolInDb() throws FileNotFoundException, IOException {
	
		File[] files = new File(path).listFiles();
		
		for (int i=0;i<files.length; i++) {
			String symbolType = files[i].getName();
			
			File[] transcriptionsSymbol = files[i].listFiles();
			for(int j=0;j<transcriptionsSymbol.length;j++) {
				String transcriptionSymbol = transcriptionsSymbol[j].getName();
				
				BufferedInputStream in = null;
				
				try {
					String transcription = transcriptionSymbol;
					String type = symbolType;
					
					Symbol symbol = new Symbol (transcription,type);
					this.insertSymbol(symbol);
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
