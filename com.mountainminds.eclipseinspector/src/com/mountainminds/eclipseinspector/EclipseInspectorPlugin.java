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
package com.mountainminds.eclipseinspector;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class EclipseInspectorPlugin extends AbstractUIPlugin {

	private static final String ID = "com.mountainminds.eclipseinspector";

	public static final String ICON_OBJ_COMPLEX = "/icons/obj16/complex_obj.gif";
	public static final String ICON_OBJ_CUSTOM = "/icons/obj16/custom_obj.gif";
	public static final String ICON_OBJ_SIMPLE = "/icons/obj16/simple_obj.gif";

	private static EclipseInspectorPlugin instance;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	@Override
	protected ImageRegistry createImageRegistry() {
		final ImageRegistry registry = super.createImageRegistry();
		registry.put(ICON_OBJ_COMPLEX,
				imageDescriptorFromPlugin(ID, ICON_OBJ_COMPLEX));
		registry.put(ICON_OBJ_CUSTOM,
				imageDescriptorFromPlugin(ID, ICON_OBJ_CUSTOM));
		registry.put(ICON_OBJ_SIMPLE,
				imageDescriptorFromPlugin(ID, ICON_OBJ_SIMPLE));
		return registry;
	}

	public static Image getImage(String path) {
		return instance.getImageRegistry().get(path);
	}

}
