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

import java.lang.reflect.Array;

/**
 * Inspector for Java arrays.
 */
class ArrayInspector implements IObjectInspector {

	@Override
	public String getValue(final Object object) {
		final Class<?> type = object.getClass().getComponentType();
		Integer len = Integer.valueOf(Array.getLength(object));
		return String.format("%s[%s]", type.getSimpleName(), len);
	}

	@Override
	public int getType(final Object object) {
		return TYPE_COMPLEX;
	}

	@Override
	public boolean hasSlots(final Object object) {
		return Array.getLength(object) > 0;
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		int idx = 0;
		final int len = Array.getLength(object);
		for (int i = 0; i < len; i++) {
			output.addSlot(String.valueOf(idx++), Array.get(object, i));
		}
	}

}
