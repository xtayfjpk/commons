package com.nightsoul.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.Closure;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public abstract class CollectionUtils {
	private static final String DEFAULT_SEPARATOR = ",";

	private CollectionUtils(){}
	
	
	public static <T> List<T> toList(Set<T> set) {
		return (List<T>) Arrays.asList(set.toArray());
	}

	public static <T> List<T> toList(Set<T> set, final Predicate<T> predicate) {
		final List<T> list = Lists.newArrayList();
		org.apache.commons.collections.CollectionUtils.forAllDo(set, new Closure() {
			@Override
			public void execute(Object input) {
				if(predicate.apply((T) input)) {
					list.add((T) input);
				}
			}
		});
		return list;
	}
	
	public static <T> List<T> toList(T[] array) {
		return Arrays.asList(array);
	}

	public static <T> List<T> toList(T[] array, final Predicate<T> predicate) {
		final List<T> list = Lists.newArrayList();
		if(ObjectUtils.isNotNull(list)) {
			for(T element : array) {
				if(ObjectUtils.isNotNull(predicate)) {
					if(predicate.apply(element)) {
						list.add(element);
					}
				} else {
					list.add(element);
				}
			}
		}
		return list;
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
	
	public static <T> List<T> toList(String value, String separator, Function<String, T> converter) {
		Iterable<String> iterable = Splitter.on(separator).split(value);
		List<T> list = Lists.newArrayList();
		for(String element : iterable) {
			if(ObjectUtils.isNull(converter)) {
				list.add((T) element);
			} else {
				list.add(converter.apply(element));
			}
		}
		return list;
	}
	public static <T> List<T> toList(String value, Function<String, T> converter) {
		return toList(value, DEFAULT_SEPARATOR, converter);
	}
	

	
	/**
	 * 判断数组是否为空，null或者length为0都视为空
	 * @param array
	 * @return
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array==null || array.length==0;
	}
	
	/**
	 * 判断数组是否非空
	 * @param array
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty(array);
	}
	
	public static <T> void remove(Collection<T> collection, Predicate<T> condition) {
		Iterator<T> iter = collection.iterator();
		while(iter.hasNext()) {
			T element = iter.next();
			if(condition.apply(element)) {
				iter.remove();
			}
		}
	}
}
