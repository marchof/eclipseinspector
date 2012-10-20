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

/**
 * Interface to inspect a object instance.
 */
public interface IObjectInspector {

	/** Simple Objekte ohne innere Struktur */
	public static final int TYPE_SIMPLE = 1;

	/** Simple Objekte mit innerer Java-standard Struktur */
	public static final int TYPE_COMPLEX = 2;

	/** Simple Objekte mit besonders aufbereiteter innerer Struktur */
	public static final int TYPE_CUSTOM = 3;

	/**
	 * Call-back to report contained slots to.
	 */
	public interface ISlotOutput {

		/**
		 * Add a slot with the given name and value..
		 */
		public void addSlot(String name, Object value);

	}

	/**
	 * Readable one-line representation of the object.
	 */
	public String getValue(Object object);

	/**
	 * Determines the Object type.
	 * 
	 * @return {@link #TYPE_SIMPLE}, {@link #TYPE_COMPLEX} or
	 *         {@link #TYPE_CUSTOM}
	 */
	public int getType(Object object);

	/**
	 * Checks whether this object has slots.
	 */
	public boolean hasSlots(Object object);

	/**
	 * Reads all slots into the given output call-back.
	 */
	public void readSlots(Object object, ISlotOutput output);

}
