/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.model;

import java.util.ArrayList;
import java.util.List;

public class JobWrapper {
	private List<Symbol> symbolList;
	public JobWrapper() {
		symbolList = new ArrayList<>();
	}
	public List<Symbol> getSymbolList() {
		return symbolList;
	}
	public void setSymbolList(List<Symbol> symbolList) {
		this.symbolList = symbolList;
	}
	
	
	
	

}
