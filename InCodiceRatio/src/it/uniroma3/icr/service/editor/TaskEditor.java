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

import it.uniroma3.icr.model.Task;
import it.uniroma3.icr.service.impl.TaskFacade;

@Component
public class TaskEditor extends PropertyEditorSupport{
	
	private @Autowired TaskFacade taskFacade;
	
	@Override
	public void setAsText(String text) {
		Task t = this.taskFacade.retrieveTask(Long.valueOf(text));
		this.setValue(t);
	}

}
