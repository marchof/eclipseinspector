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

import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

import com.mountainminds.eclipseinspector.core.objectinspector.IObjectInspector;
import com.mountainminds.eclipseinspector.core.objectinspector.InspectorRegistry;

public abstract class AbstractObjectBrowserPropertyPage extends PropertyPage {

	@Override
	protected Control createContents(Composite parent) {
		noDefaultAndApplyButton();
		IObjectInspector inspector = InspectorRegistry.DEFAULT;

		TreeViewer viewer = new TreeViewer(parent, SWT.BORDER);
		viewer.getTree().setLinesVisible(true);
		viewer.getTree().setHeaderVisible(true);
		viewer.setContentProvider(new InspectionContentProvider(inspector));

		TreeViewerColumn nameColumn = new TreeViewerColumn(viewer, SWT.LEFT);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(200);
		nameColumn.setLabelProvider(new InspectionNameLabelProvider(inspector));

		TreeViewerColumn valueColumn = new TreeViewerColumn(viewer, SWT.LEFT);
		valueColumn.getColumn().setText("Value");
		valueColumn.getColumn().setWidth(200);
		valueColumn
				.setLabelProvider(new InspectionValueLabelProvider(inspector));

		viewer.setInput(getRoots());

		return viewer.getTree();
	}

	protected abstract Collection<NamedObject> getRoots();

}
