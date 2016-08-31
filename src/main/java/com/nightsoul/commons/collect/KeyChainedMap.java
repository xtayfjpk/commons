package com.nightsoul.commons.collect;

import java.util.Map;

/**
 * 链式key Map
 * @author zj
 *
 * @param <V> 值类型
 */
public interface KeyChainedMap<V> extends Map<String, V> {
	
	/**
	 * 返回Map类型数据
	 * @return
	 */
	Map<String, Object> getData();
}
