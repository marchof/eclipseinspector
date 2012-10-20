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
package com.mountainminds.eclipseinspector.core.objectinspector;

/**
 * Inspector for objects without any inner structure, represented only by their
 * {@link Object#toString()} value.
 */
public class SimpleTypeInspector implements IObjectInspector {

	@Override
	public String getValue(final Object object) {
		return String.valueOf(object);
	}

	@Override
	public int getType(final Object object) {
		return TYPE_SIMPLE;
	}

	@Override
	public boolean hasSlots(final Object object) {
		return false;
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
	}

}
