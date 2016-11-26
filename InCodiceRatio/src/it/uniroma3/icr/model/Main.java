package it.uniroma3.icr.model;

import java.io.File;

public class Main {
	
	private static final String path="C:\\Users\\NandG\\Documents\\apache-tomcat-7.0.54\\webapps\\InCodiceRatio\\resources\\img\\sources\\training\\ciao\\";
	public static void main(String[] args) {
		String newPath = path.replace(File.separator, "/");
		
		String[] parts = newPath.split("/");
		System.out.println(parts[11]);
		
		System.out.println(newPath.substring(77, newPath.length()));
		
		
		
	}

}
