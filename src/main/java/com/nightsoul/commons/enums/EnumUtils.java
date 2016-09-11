/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package com.nightsoul.commons.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.nightsoul.commons.util.ObjectUtils;

public class EnumUtils {

	public static <Type extends Enum<Type>> boolean isEnum(Class<Type> e, String s) {
		if(ObjectUtils.isNull(s)){
			return false;
		}
		try {
			Enum.valueOf(e, s);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	public static <Type extends Enum<Type>> Optional<Type> toEnum(Class<Type> e, String s) {
		if(ObjectUtils.isNull(s)){
			return Optional.absent();
		}
		try {
			return Optional.fromNullable(Enum.valueOf(e, s));
		} catch (IllegalArgumentException ex) {
			return Optional.absent();
		}
	}

	public static <Type extends Enum<Type>> ArrayList<Type> toEnum(Class<Type> e, String[] values) {
		ArrayList<Type> enumValues = new ArrayList<Type>();
		for (String value : values) {
			Optional<Type> enumValue = toEnum(e, value);
			if (enumValue.isPresent()) {
				enumValues.add(enumValue.get());
			}
		}
		if (enumValues.isEmpty()) {
			return Lists.newArrayList();
		} else {
			return enumValues;
		}
	}

	public static <Type extends Enum<Type>> Optional<Type> toEnum(Type[] es, int ordinal) {
		for (Type typeValue : es) {
			if (typeValue.ordinal() == ordinal) {
				return Optional.of(typeValue);
			}
		}
		return Optional.absent();
	}

	public static Enum<?> getInstanceFromField(Field field) {
		Object[] enumValues = field.getType().getEnumConstants();
		return (Enum<?>) enumValues[0];
	}

	public static Enum<?>[] getValuesFromField(Field field) {
		Object[] enumValues = field.getType().getEnumConstants();
		return (Enum[]) enumValues;
	}
}
