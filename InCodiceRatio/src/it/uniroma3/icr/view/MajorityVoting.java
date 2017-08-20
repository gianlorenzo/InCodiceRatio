/*******************************************************************************
 * Copyright (c) 2017 Gianlorenzo Didonato.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package it.uniroma3.icr.view;

public class MajorityVoting {
	
	private int imageId;
	
	private String transcription;
	
	private int numberOfYes;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

	public int getNumberOfYes() {
		return numberOfYes;
	}

	public void setNumberOfYes(int numberOfYes) {
		this.numberOfYes = numberOfYes;
	}
	
	

}
