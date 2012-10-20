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

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for multiple inspectors.
 */
public class InspectorRegistry implements IObjectInspector {

	public static final IObjectInspector DEFAULT;

	static {
		final InspectorRegistry registry = new InspectorRegistry();

		registry.add(java.lang.String.class, new SimpleTypeInspector());
		registry.add(java.lang.Number.class, new SimpleTypeInspector());
		registry.add(java.lang.Boolean.class, new SimpleTypeInspector());
		registry.add(java.lang.Character.class, new SimpleTypeInspector());
		registry.add(java.lang.Class.class, new SimpleTypeInspector());
		registry.add(java.lang.Enum.class, new SimpleTypeInspector());
		registry.add(java.util.Collection.class, new CollectionInspector());
		registry.add(java.util.Map.class, new MapInspector());
		registry.add(java.util.Map.Entry.class, new MapEntryInspector());

		DEFAULT = registry;
	}

	private static final IObjectInspector NULL_INSPECTOR = new SimpleTypeInspector();
	private static final IObjectInspector DEFAULT_INSPECTOR = new GenericJavaObjectInspector();

	private static final IObjectInspector ARRAY_INSPECTOR = new ArrayInspector();

	private final Map<Class<?>, IObjectInspector> inspectors;

	public InspectorRegistry() {
		inspectors = new HashMap<Class<?>, IObjectInspector>();
	}

	public void add(Class<?> type, IObjectInspector inspector) {
		inspectors.put(type, inspector);
	}

	private IObjectInspector findRegisteredInspector(final Class<?> type) {
		if (type == null) {
			return null;
		}
		// Exact match
		IObjectInspector i = inspectors.get(type);
		if (i != null) {
			return i;
		}
		// Class hierarchy
		i = findRegisteredInspector(type.getSuperclass());
		if (i != null) {
			return i;
		}
		// Interfaces
		for (final Class<?> interfacetype : type.getInterfaces()) {
			i = findRegisteredInspector(interfacetype);
			if (i != null) {
				return i;
			}
		}
		return null;
	}

	private IObjectInspector findInspector(final Object object) {
		if (object == null) {
			return NULL_INSPECTOR;
		}
		if (object instanceof Object[]) {
			return ARRAY_INSPECTOR;
		}
		final IObjectInspector i = findRegisteredInspector(object.getClass());
		return i == null ? DEFAULT_INSPECTOR : i;
	}

	// IObjectInspector

	@Override
	public String getValue(final Object object) {
		return findInspector(object).getValue(object);
	}

	@Override
	public int getType(final Object object) {
		return findInspector(object).getType(object);
	}

	@Override
	public boolean hasSlots(final Object object) {
		return findInspector(object).hasSlots(object);
	}

	@Override
	public void readSlots(final Object object, final ISlotOutput output) {
		findInspector(object).readSlots(object, output);
	}

}
