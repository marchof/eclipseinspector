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
package com.mountainminds.eclipseinspector.ui.layout;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ColorLayoutHandler extends AbstractHandler {

	private final Color[] pool = new Color[16];

	private int idx = 0;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		setBackground(shell);
		return null;
	}

	private void setBackground(Composite composite) {
		composite.setBackground(getRandomColor(composite.getDisplay()));
		for (Control child : composite.getChildren()) {
			if (child instanceof Composite) {
				setBackground((Composite) child);
			}
		}
	}

	private Color getRandomColor(Device device) {
		idx = (idx + 1) % pool.length;
		Color c = pool[idx];
		if (c == null) {
			float hue = 360.0f * idx / pool.length;
			c = new Color(device, new RGB(hue, 0.2f, 0.9f));
			pool[idx] = c;
		}
		return c;
	}
}
