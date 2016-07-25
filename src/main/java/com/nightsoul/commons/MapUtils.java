package com.nightsoul.commons;

import java.util.Map;


public abstract class MapUtils {
	private MapUtils(){}
	
	public static <K>  void addShortValue(Map<K, Short> map, K key, short offset) {
		map.put(key, (short) (map.get(key)==null ? 0 : map.get(key) + offset));
	}

	public static <K>  void addIntValue(Map<K, Integer> map, K key, int offset) {
		map.put(key, map.get(key)==null ? 0 : map.get(key) + offset);
	}

	public static <K>  void addLongValue(Map<K, Long> map, K key, long offset) {
		map.put(key, map.get(key)==null ? 0 : map.get(key) + offset);
	}

	public static <K>  void addFloatValue(Map<K, Float> map, K key, float offset) {
		map.put(key, map.get(key)==null ? 0 : map.get(key) + offset);
	}

	public static <K>  void addDoubleValue(Map<K, Double> map, K key, double offset) {
		map.put(key, map.get(key)==null ? 0 : map.get(key) + offset);
	}
}
