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

import java.util.Collection;

/**
 * Inspector for {@link Collection} objects.
 */
public class CollectionInspector implements IObjectInspector {

	@Override
	public String getValue(final Object object) {
		String type = object.getClass().getSimpleName();
		int len = Integer.valueOf(((Collection<?>) object).size());
		return String.format("%s[%s]", type, len);
	}

	@Override
	public int getType(final Object object) {
		return TYPE_CUSTOM;
	}

	@Override
	public boolean hasSlots(final Object object) {
		return !((Collection<?>) object).isEmpty();
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		int idx = 0;
		for (final Object element : (Collection<?>) object) {
			output.addSlot(String.valueOf(idx++), element);
		}
	}

}
