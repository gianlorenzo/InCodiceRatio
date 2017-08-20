/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.model;

import java.util.Comparator;

public class ComparatoreSimboloPerNome implements Comparator<Symbol> {

	@Override
	public int compare(Symbol s1, Symbol s2) {
		return s1.getTranscription().compareTo(s2.getTranscription());
	}

}
