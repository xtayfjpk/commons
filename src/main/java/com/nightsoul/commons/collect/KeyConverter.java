package com.nightsoul.commons.collect;

/**
 * 键转换器，以获取与添加值时有效；键为要放置进ChainedKeyMap中的完整key
 * @author zj
 *
 */
public interface KeyConverter {
	
	/**
	 * 
	 * @param key 放置进ChainedKeyMap中的完整key，以 "."号分隔
	 * @return 转换后的key，以返回值为准
	 */
	String convert(String key);
}
