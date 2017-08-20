/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.model;

import java.io.File;

public class Main {
	
	private static final String path="C:\\Users\\NandG\\Documents\\apache-tomcat-9.0.0.M21\\webapps\\InCodiceRatio\\resources\\img\\training\\ciao\\";
	public static void main(String[] args) {
		String newPath = path.replace(File.separator, "/");
		
		String[] parts = newPath.split("/");
		System.out.println(parts[10]);
		
		System.out.println(newPath.substring(80, newPath.length()));
		
		
		
	}

}
