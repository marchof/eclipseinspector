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
import java.util.Collections;

public class DebugPropertyPage extends AbstractObjectBrowserPropertyPage {

	@Override
	protected Collection<NamedObject> getRoots() {
		return Collections
				.singleton(new NamedObject("selection", getElement()));
	}
}
