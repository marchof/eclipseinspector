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

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import com.mountainminds.eclipseinspector.EclipseInspectorPlugin;
import com.mountainminds.eclipseinspector.core.objectinspector.IObjectInspector;

public class InspectionNameLabelProvider extends CellLabelProvider {

	private final IObjectInspector inspector;

	public InspectionNameLabelProvider(IObjectInspector inspector) {
		this.inspector = inspector;
	}

	@Override
	public void update(ViewerCell cell) {
		final NamedObject entry = ((NamedObject) cell.getElement());
		cell.setText(entry.getName());
		switch (inspector.getType(entry.getObject())) {
		case IObjectInspector.TYPE_CUSTOM:
			cell.setImage(EclipseInspectorPlugin
					.getImage(EclipseInspectorPlugin.ICON_OBJ_CUSTOM));
			break;
		case IObjectInspector.TYPE_COMPLEX:
			cell.setImage(EclipseInspectorPlugin
					.getImage(EclipseInspectorPlugin.ICON_OBJ_COMPLEX));
			break;
		case IObjectInspector.TYPE_SIMPLE:
			cell.setImage(EclipseInspectorPlugin
					.getImage(EclipseInspectorPlugin.ICON_OBJ_SIMPLE));
			break;
		}
	}

}
