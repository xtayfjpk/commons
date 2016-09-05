package com.nightsoul.commons.util;

import org.apache.commons.lang.StringUtils;


public abstract class PathUtils {
	private PathUtils(){}
	
	
	public static boolean endsWithSplash(String path) {
		if(StringUtils.isEmpty(path)) {
			return false;
		}
		return path.endsWith("/") || path.endsWith("\\");
	}
}
