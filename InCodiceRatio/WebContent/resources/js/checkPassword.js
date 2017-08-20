/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
function matchpass(){  
	var firstpassword=document.f1.password.value;  
	var secondpassword=document.f1.password2.value;  
	

	if(firstpassword==secondpassword){  
		return true;  
	}  
	else{  
		alert("Le password non coincidono!");  
		return false;  
	}  
}  