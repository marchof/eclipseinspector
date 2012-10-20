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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Inspector for arbitrary Java objects. The structure is reflected by the
 * members of the object.
 */
public class GenericJavaObjectInspector implements IObjectInspector {

	@Override
	public String getValue(final Object object) {
		return object.getClass().getSimpleName();
	}

	@Override
	public int getType(final Object object) {
		return TYPE_COMPLEX;
	}

	@Override
	public boolean hasSlots(final Object object) {
		return true;
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		for (final Field f : getAllFields(object.getClass())) {
			try {
				f.setAccessible(true);
				final Object value = f.get(object);
				output.addSlot(f.getName(), value);
			} catch (final IllegalAccessException e) {
				output.addSlot(f.getName(), "<not accessible>");
			}
		}
	}

	private Field[] getAllFields(Class<? extends Object> type) {
		final List<Field> fields = new ArrayList<Field>();
		for (; type != null; type = type.getSuperclass()) {
			for (final Field f : type.getDeclaredFields()) {
				if (!f.isSynthetic()
						&& (f.getModifiers() & Modifier.STATIC) == 0) {
					fields.add(f);
				}
			}
		}
		Collections.sort(fields, new Comparator<Field>() {

			@Override
			public int compare(final Field f1, final Field f2) {
				return f1.getName().compareTo(f2.getName());
			}
		});
		return fields.toArray(new Field[0]);
	}

}
