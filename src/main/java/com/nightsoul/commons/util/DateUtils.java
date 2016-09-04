package com.nightsoul.commons.util;

import java.util.Date;



public abstract class DateUtils {
	private DateUtils(){}
	
	/**
	 * 返回当前日期
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * 获取一天毫秒数
	 * @return
	 */
	public static long getOneDayMillis() {
		return 24 * 60 * 60 * 1000;
	}
}
