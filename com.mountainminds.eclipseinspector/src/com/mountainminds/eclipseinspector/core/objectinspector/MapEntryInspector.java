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
 * Inspector for {@link Map.Entry}-Objekte.
 */
public class MapEntryInspector implements IObjectInspector {

	@Override
	public int getType(final Object object) {
		return TYPE_CUSTOM;
	}

	@Override
	public String getValue(final Object object) {
		return "Map.Entry";
	}

	@Override
	public boolean hasSlots(final Object object) {
		return true;
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		final Map.Entry<?, ?> entry = (Map.Entry<?, ?>) object;
		output.addSlot("key", entry.getKey());
		output.addSlot("value", entry.getValue());
	}

}
