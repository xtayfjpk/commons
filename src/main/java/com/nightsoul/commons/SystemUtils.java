package com.nightsoul.commons;


public abstract class SystemUtils {
	private SystemUtils(){}
	
	public static boolean hasProperty(String key) {
		return null != System.getProperty(key);
	}
}
