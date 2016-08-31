package com.nightsoul.commons.collect;

/**
 * KeySplit转换器，以获取与添加值时有效；KeySplit为完整key用"."号进行分隔后所得到元素
 * @author zj
 *
 */
public interface KeySplitConverter {

	/**
	 * 
	 * @param keySplit 完整key用"."号进行分隔后所得到元素
	 * @return
	 */
	String convert(String keySplit);
}
