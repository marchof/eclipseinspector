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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.mountainminds.eclipseinspector.core.objectinspector.IObjectInspector;

/**
 * Content provider based an {@link IObjectInspector}.
 */
public class InspectionContentProvider extends ArrayContentProvider implements
		ITreeContentProvider {

	private final IObjectInspector inspector;

	public InspectionContentProvider(IObjectInspector inspector) {
		this.inspector = inspector;
	}

	@Override
	public Object[] getChildren(final Object element) {
		final Object value = ((NamedObject) element).getObject();
		final List<NamedObject> children = new ArrayList<NamedObject>();
		inspector.readSlots(value, new IObjectInspector.ISlotOutput() {
			@Override
			public void addSlot(final String name, final Object value) {
				children.add(new NamedObject(name, value));
			}
		});
		return children.toArray();
	}

	@Override
	public boolean hasChildren(final Object element) {
		final Object value = ((NamedObject) element).getObject();
		return inspector.hasSlots(value);
	}

	@Override
	public Object getParent(final Object element) {
		return null;
	}

}
