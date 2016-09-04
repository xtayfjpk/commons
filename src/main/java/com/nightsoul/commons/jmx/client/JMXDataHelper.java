package com.nightsoul.commons.jmx.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

public abstract class JMXDataHelper {
	
	public static <T> List<T> arrayToList(T[] values) {
		if(values==null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for(T value : values) {
			list.add(value);
		}
		return list;
	}
	
	public static <T> T uniqueResult(Collection<T> values) {
		if(values==null || values.size()==0) {
			return null;
		}
		return values.iterator().next();
	}
	
	public static List<Map<String, Object>> parseTabularData(TabularData data) {
		List<Map<String, Object>> tmps = new ArrayList<Map<String, Object>>();
		Iterator<?> valueIter = data.values().iterator();
		while (valueIter.hasNext()) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			CompositeData compositeData = (CompositeData) valueIter.next();
			Iterator<?> iter = compositeData.getCompositeType().keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				tmp.put(key, compositeData.get(key));
				tmps.add(tmp);
			}
		}
		return tmps;
	}
	
	public static Map<String, Object> parseCompositeData(CompositeData data) {
		Iterator<String> iter = data.getCompositeType().keySet().iterator();
		Map<String, Object> tmp = new HashMap<String, Object>();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			tmp.put(key, data.get(key));
		}
		return tmp;
	}
}
