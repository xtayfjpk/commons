package com.nightsoul.commons.util;

import java.text.SimpleDateFormat;
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
	
	/**
	 * 格式化日期
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期
	 * @param dateMillis 日期毫秒数
	 * @param pattern
	 * @return
	 */
	public static String formatDate(long dateMillis, String pattern) {
		return formatDate(new Date(dateMillis), pattern);
	}

	/**
	 * 格式化当前日期
	 * @param pattern
	 * @return
	 */
	public static String formatDate(String pattern) {
		return formatDate(new Date(), pattern);
	}
}
