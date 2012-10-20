/*******************************************************************************
 * Copyright (c) 2012 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *    
 *******************************************************************************/
package com.mountainminds.eclipseinspector.ui.debug;

class NamedObject {

	private final String name;

	private final Object object;

	public NamedObject(String name, Object object) {
		this.name = name;
		this.object = object;
	}

	public String getName() {
		return name;
	}

	public Object getObject() {
		return object;
	}

}
