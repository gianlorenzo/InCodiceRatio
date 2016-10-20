package it.uniroma3.icr.model;

import java.io.File;

public class Main {
	private static final String path ="C:\\Users\\NandG\\Documents\\img\\sources\\samples\\";


	
	
	public static void main(String[] args) {
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
						
						System.out.println(pageName+manuscriptName+typeName+nameComplete);
					}
	}
			}
		}
	}
}

