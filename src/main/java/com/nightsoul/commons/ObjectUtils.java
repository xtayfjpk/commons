package com.nightsoul.commons;



public abstract class ObjectUtils {
	private ObjectUtils(){}
	
	
	
	public static String toString(byte value) {
		return Byte.valueOf(value).toString();
	}
	public static String toString(short value) {
		return Short.valueOf(value).toString();
	}
	public static String toString(int value) {
		return Integer.valueOf(value).toString();
	}
	
	public static String toString(long value) {
		return Long.valueOf(value).toString();
	}
	
	public static String toString(float value) {
		return Float.valueOf(value).toString();
	}
	
	public static String toString(double value) {
		return Double.valueOf(value).toString();
	}
	public static String toString(char value) {
		return Character.valueOf(value).toString();
	}
	public static String toString(boolean value) {
		return Boolean.valueOf(value).toString();
	}
	
	public static String toString(Object value) {
		return isNull(value) ? null : value.toString();
	}
	
	public static boolean isNull(Object value) {
		return value==null;
	}
	
	public static boolean isNotNull(Object value) {
		return !isNull(value);
	}
}
