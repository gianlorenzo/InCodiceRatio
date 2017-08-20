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

public class ComparatorePerData implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		return t1.getStartDate().compareTo(t2.getStartDate());
	}

}
