package com.nightsoul.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//已完成
public abstract class ObjectValue {
	private static final String[] DATE_FORMATS = { "yyyy-MM-dd hh:mm:ss", "yyyy-MM-dd" };

	public static Integer getInteger(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if (value instanceof Integer) {
			return ((Integer) value);
		}

		if (value instanceof Long) {
			return Integer.valueOf(((Long) value).intValue());
		}

		if (value instanceof Double) {
			return Integer.valueOf(((Double) value).intValue());
		}

		return Integer.valueOf(Integer.parseInt(value.toString()));
	}

	public static Integer getInteger(Object value) {
		return getInteger(value, null);
	}
	
	public static Long getLong(Object value, Long defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if (value instanceof Long) {
			return ((Long) value);
		}

		return Long.valueOf(Long.parseLong(value.toString()));
	}

	public static Long getLong(Object value) {
		return getLong(value, null);
	}

	public static Double getDouble(Object value, Double defaultValue) {
		if (value == null)
			return defaultValue;

		if (value instanceof Double)
			return ((Double) value);

		return Double.valueOf(Double.parseDouble(value.toString()));
	}

	public static Double getDouble(Object value) {
		return getDouble(value, null);
	}
	
	public static Float getFloat(Object value, Float defaultValue) {
		if (value == null)
			return defaultValue;

		if (value instanceof Float)
			return ((Float) value);

		return Float.valueOf(Float.parseFloat(value.toString()));
	}

	public static Float getFloat(Object value) {
		return getFloat(value, null);
	}
	
	public static Boolean getBoolean(Object value, Boolean defaultValue) {
		if (value == null)
			return defaultValue;

		if (value instanceof Boolean)
			return ((Boolean) value);

		try {
			Integer result = Integer.valueOf(Integer.parseInt(value.toString()));
			return Boolean.valueOf(result.intValue() > 0);
		} catch (Exception e) {
			if (value.toString().equalsIgnoreCase("true"))
				return Boolean.valueOf(true);

			if (value.toString().equalsIgnoreCase("false"))
				return Boolean.valueOf(false);

			if (value.toString().equalsIgnoreCase("on"))
				return Boolean.valueOf(true);

			if (value.toString().equalsIgnoreCase("off"))
				return Boolean.valueOf(false);
		}

		return defaultValue;
	}

	public static Boolean getBoolean(Object value) {
		return getBoolean(value, null);
	}
	
	public static String getString(Object value, String defaultValue) {
		if (value == null)
			return defaultValue;

		return value.toString();
	}

	public static String getString(Object value) {
		return getString(value, null);
	}
	
	public static Date getDate(Object value, Date defaultValue)
			throws ParseException {
		if (value == null)
			return defaultValue;

		if (value instanceof Date) {
			return ((Date) value);
		}

		return new SimpleDateFormat(DATE_FORMATS[0]).parse(value.toString());
	}

	public static Date getDate(Object value) throws ParseException {
		return getDate(value, null);
	}
}
