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
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;

public class AdaptersPropertyPage extends AbstractObjectBrowserPropertyPage {

	@Override
	protected Collection<NamedObject> getRoots() {
		IAdapterManager manager = Platform.getAdapterManager();
		final IAdaptable element = getElement();
		final List<NamedObject> result = new ArrayList<NamedObject>();
		for (String type : manager.computeAdapterTypes(element.getClass())) {
			Object adapter = manager.getAdapter(element, type);
			if (adapter != null) {
				result.add(new NamedObject(type, adapter));
			}
		}
		return result;
	}

}
