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

import java.util.Map;

/**
 * Inspector for maps.
 */
public class MapInspector implements IObjectInspector {

	@Override
	public String getValue(final Object object) {
		String type = object.getClass().getSimpleName();
		int len = Integer.valueOf(((Map<?, ?>) object).size());
		return String.format("%s[%s]", type, len);
	}

	@Override
	public int getType(final Object object) {
		return TYPE_CUSTOM;
	}

	@Override
	public boolean hasSlots(final Object object) {
		return !((Map<?, ?>) object).isEmpty();
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		int idx = 0;

		final Map<?, ?> map = (Map<?, ?>) object;
		try {
			for (final Map.Entry<?, ?> e : map.entrySet()) {
				final Object key = e.getKey();
				final Object value = e.getValue();
				if (isSimpleKey(key)) {
					output.addSlot(String.valueOf(key), value);
				} else {
					output.addSlot(String.valueOf(idx++), e);
				}
			}
		} catch (final UnsupportedOperationException e) {
			// Some Map internal implementations do not support all operations
			output.addSlot(String.valueOf(object), null);
		}
	}

	private boolean isSimpleKey(final Object object) {
		final IObjectInspector registry = InspectorRegistry.DEFAULT;
		return registry.getType(object) == IObjectInspector.TYPE_SIMPLE;
	}

}
