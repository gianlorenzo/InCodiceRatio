/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.insertImageInDb;




import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import it.uniroma3.icr.model.Symbol;

@Repository
public class InsertSymbolInDb {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertSymbol(Symbol symbol) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(symbol);
		session.getTransaction().commit();
		session.close();
	}


	public void insertSymbolInDb(String p) throws FileNotFoundException, IOException {

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
				File image = images[0];
				String nameComplete = image.getName();
				String name = FilenameUtils.getBaseName(nameComplete);
				String parts[] = name.split("_");
				
				int width = Integer.valueOf(parts[0]);
				
				BufferedInputStream in = null;
				
				try {
					String transcription = transcriptionSymbol;
					String type = typeSymbol;
					String manuscript = finalManuscript;
					
					Symbol symbol = new Symbol(transcription,type,manuscript,width);
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


