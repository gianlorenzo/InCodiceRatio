/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.service.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.uniroma3.icr.model.Symbol;
import it.uniroma3.icr.service.impl.SymbolFacade;

@Component
public class SymbolEditor extends PropertyEditorSupport {
	
	private @Autowired SymbolFacade symbolFacade;
	
	@Override
	public void setAsText(String text) {
		Symbol s = this.symbolFacade.retrieveSymbol(Long.valueOf(text));
		this.setValue(s);
	}

}
