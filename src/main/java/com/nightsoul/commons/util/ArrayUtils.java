package com.nightsoul.commons.util;

public abstract class ArrayUtils {
	private ArrayUtils() {}
	
	public static <T> T[] toArray(T... values) {
		return values;
	}
	
	public static boolean isEmpty(Object[] array) {
		if(ObjectUtils.isNull(array) || array.length == 0){
			return true;
		}
		for(Object value : array){
			if(ObjectUtils.isNotNull(value)){
				return false;
			}
		}
		return true;
	}
	
	public static <T> boolean contains(T[] array, T object) {
		for(T value : array){
			if(object.equals(value)){
				return true;
			}
		}
		return false;
	}
	
	public static int getLength(Object[] array) {
		if(ObjectUtils.isNull(array)){
			return 0;
		}
		return array.length;
	}
}
