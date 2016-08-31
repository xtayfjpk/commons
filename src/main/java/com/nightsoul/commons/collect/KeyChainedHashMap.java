package com.nightsoul.commons.collect;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public class KeyChainedHashMap<V> implements KeyChainedMap<V> {
	public static final String TRANSFORMER_SEPARATOR = "#";
	public static final String TRANSFORMERS_SYMBOL = "transformers";
	public static final String DOT_PATTERN = "\\.";
	private Map<String, Object> data = new HashMap<String, Object>();
	private List<KeyConverter> keyConverters = null;
	private List<KeySplitConverter> keySplitConverters = null;
	
	public KeyChainedHashMap() {
	}
	
	public KeyChainedHashMap(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public int size() {
		return this.keySet().size();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.get(key)!=null;
	}

	@Override
	public boolean containsValue(Object value) {
		return this.values().contains(value);
	}

	@Override
	public V get(Object key) {
		if(key==null) {
			return (V) this.data.get(null);
		}
		key = convertKey(key.toString());//获取之前执行key转换操作
		String[] keySplits = key.toString().split(DOT_PATTERN);
		if(keySplits.length==0) {
			return null;
		}
		return getValue(data, 0, keySplits);
	}
	
	private V getValue(Map<String, Object> data, int index, String[] keySplits) {
		String keySplit = keySplits[index];
		keySplit = convertKeySplit(keySplit);//获取之前执行keySplit转换操作
		Object value = data.get(keySplit);
		if(value!=null) {
			if(index==keySplits.length-1) {//说明遍历到了最后一个key，该值是要要返回的值
				return (V) value;
			} else {//如果不是则进行下一步，此时value就为一个Map
				return getValue((Map<String, Object>) value, index + 1, keySplits);
			}
		}
		return null;
	}
	
	@Override
	public V put(String key, V value) {
		if(key==null) {
			return (V) this.data.put(null, value);
		}
		key = convertKey(key);//获取之前执行key转换操作
		String[] keySplits = key.toString().split(DOT_PATTERN);
		Map<String, Object> targetMap = getTargetMap(data, 0, keySplits);
		return (V) targetMap.put(keySplits[keySplits.length-1], value);
	}

	
	private Map<String, Object> getTargetMap(Map<String, Object> source, int index, String[] keySplits) {
		if(index==keySplits.length-1) {
			return source;
		}
		String keySplit = keySplits[index];
		keySplit = convertKeySplit(keySplit);//获取之前执行keySplit转换操作
		Object value = source.get(keySplit);
		Map<String, Object> next = null;
		if(value==null) {//判断目标Map是否已经存在
			next = new HashMap<String, Object>();
			source.put(keySplit, next);
		} else {
			next = (Map<String, Object>) value;
		}
		return getTargetMap(next, index + 1, keySplits);
	}

	@Override
	public V remove(Object key) {
		if(key==null) {
			return (V) this.data.remove(null);
		}
		String[] keySplits = key.toString().split(DOT_PATTERN);
		Map<String, Object> targetMap = this.getTargetMap(data, 0, keySplits);
		return (V) targetMap.remove(keySplits[keySplits.length-1]);
	}

	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		for(Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		this.data.clear();		
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		populateKeys(data, keySet, "");
		return keySet;
	}
	
	private void populateKeys(Map<String, Object> source, Set<String> keys, String previousKey) {
		for(String key : source.keySet()) {
			Object value = source.get(key);
			String prevKey = addKey(keys, key, previousKey);
			if(value instanceof Map) {
				populateKeys((Map<String, Object>) value, keys, prevKey);
				//如果类型为Map，说明后面还有keySplit，则从keys中移除上一步key
				keys.remove(prevKey);
			}
		}
	}
	private String addKey(Set<String> keys, String key, String previousKey) {
		String newKey = previousKey + (previousKey.isEmpty() ? previousKey : ".") + key;//新key为上一步key + 当前keySplit
		keys.add(newKey);
		return newKey;
	}

	@Override
	public Collection<V> values() {
		Set<V> values = new HashSet<V>();
		for(String key : this.keySet()) {
			values.add(this.get(key));
		}
		return values();
	}

	@Override
	public Set<java.util.Map.Entry<String, V>> entrySet() {
		Set<Map.Entry<String, V>> entrySet = new HashSet<Map.Entry<String, V>>();
		for(final String key : this.keySet()) {
			Map.Entry<String, V> entry = new Map.Entry<String, V>() {

				@Override
				public String getKey() {
					return key;
				}

				@Override
				public V getValue() {
					return KeyChainedHashMap.this.get(key);
				}

				@Override
				public V setValue(V value) {
					return KeyChainedHashMap.this.put(key, value);
				}
			};
			entrySet.add(entry);
		}
		
		return entrySet;
	}
	
	
	//转换key
	private String convertKey(String key) {
		for(KeyConverter converter : getKeyConverters()) {
			key = converter.convert(key);
		}
		return key;
	}
	
	//转换keySplit
	private String convertKeySplit(String keySplit) {
		for(KeySplitConverter converter : getKeySplitConverters()) {
			keySplit = converter.convert(keySplit);
		}
		return keySplit;
	}
	
	@Override
	public Map<String, Object> getData() {
		return this.data;
	}
	
	public List<KeyConverter> getKeyConverters() {
		if(keyConverters==null) {
			keyConverters = Lists.newArrayList();
		}
		return keyConverters;
	}

	public List<KeySplitConverter> getKeySplitConverters() {
		if(keySplitConverters==null) {
			keySplitConverters = Lists.newArrayList();
		}
		return keySplitConverters;
	}


}
