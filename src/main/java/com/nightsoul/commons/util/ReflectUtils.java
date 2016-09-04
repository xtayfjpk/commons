package com.nightsoul.commons.util;

public abstract class ReflectUtils {
	
	public static boolean isImplementInterface(Class<?> clazz, Class<?> interfaceClazz) {
		Class<?>[] clazzes = clazz.getInterfaces();
		if(null==clazzes) {
			return false;
		}
		
		for(Class<?> c : clazzes) {
			if(c.equals(interfaceClazz)) {
				return true;
			}
			return isImplementInterface(c, interfaceClazz);
		}
		
		Class<?> tmp = clazz.getSuperclass();
		if(null==tmp) {
			return false;
		}
		return isImplementInterface(tmp, interfaceClazz);
	}
	
}
