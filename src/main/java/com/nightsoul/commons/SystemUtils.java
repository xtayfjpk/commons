package com.nightsoul.commons;


public abstract class SystemUtils {
	private SystemUtils(){}
	
	public static boolean hasProperty(String key) {
		return ObjectUtils.isNotNull(System.getProperty(key));
	}

	public static boolean noProperty(String key) {
		return !hasProperty(key);
	}
}
