package com.nightsoul.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public abstract class CollectionUtils {
	private static final String DEFAULT_SEPARATOR = ",";

	private CollectionUtils(){}
	
	
	public static <T> List<T> toList(Set<T> set) {
		return (List<T>) Arrays.asList(set.toArray());
	}
	
	public static <T> List<T> toList(T[] array) {
		return Arrays.asList(array);
	}
	
	public static List<String> toStringList(String value) {
		return toStringList(value, DEFAULT_SEPARATOR);
	}

	public static List<String> toStringList(String value, String separator) {
		if(StringUtils.isEmpty(value)) {
			return Collections.EMPTY_LIST;
		}
		return toList(value.split(separator));
	}

	public static List<Integer> toIntegerList(String numberString) {
		return toIntegerList(numberString, DEFAULT_SEPARATOR);
	}

	public static List<Integer> toIntegerList(String numberString, String separator) {
		if(StringUtils.isEmpty(numberString)) {
			return Collections.EMPTY_LIST;
		}
		List<Integer> integers = new ArrayList<Integer>();
		for(String value : numberString.split(separator)) {
			integers.add(Integer.valueOf(value));
		}
		return integers;
	}
	
	
	
}
