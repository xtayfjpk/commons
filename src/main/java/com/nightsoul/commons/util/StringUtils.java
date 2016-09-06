package com.nightsoul.commons.util;

import com.google.common.base.Preconditions;

public abstract class StringUtils {
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String TABLE = "\t";
	public static final String RETURN = "\r";
	public static final String NEWLINE = "\n";
	public static final String RN = "\r\n";
	public static final String COMMA = ",";
	
	private StringUtils(){}
	
	public static boolean isTrimedEmpty(String value) {
		return ObjectUtils.isNull(value) || value.trim().isEmpty();
	}
	
	public static boolean isTrimedNotEmpty(String value) {
		return !isTrimedEmpty(value);
	}
	
	public static String trimLineBreak(String value) {
		return ObjectUtils.isNull(value) ? value : value.replaceAll(NEWLINE, EMPTY).replaceAll(RETURN, EMPTY);
	}
	
	public static String lineBreakToBr(String value) {
		return ObjectUtils.isNull(value) ? value : value.replaceAll(RN, "<br/>").replaceAll(NEWLINE, "<br/>");
	}
	
	public static String ascii2native(String ascii) {
		if(-1 == ascii.indexOf("\\u")) {
			return ascii;
		}
		
		StringBuilder sb = new StringBuilder();
		int i = -1, pos = 0;
		
		while((i=ascii.indexOf("\\u", pos)) != -1) {
			sb.append(ascii.substring(pos, i));
			if(i+5 < ascii.length()) {
				pos = i + 6;
				String code = ascii.substring(i+2, i+6);
				char ch = (char)Integer.parseInt(code, 16);
				sb.append(ch);
			}
		}
		
		sb.append(ascii.substring(pos));
		return sb.toString();
	}
	
	public static String native2ascii(String value) {
		if(ObjectUtils.isNotNull(value)) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<value.length(); i++) {
				char ch = value.charAt(i);
				if(Compares.notLess(ch, 19968) && Compares.notGreater(ch, 171941)) {
					sb.append("\\u").append(Integer.toHexString(ch));
				} else {
					sb.append(ch);
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	public static boolean startsWithIgnoreCase(String value, String prefix) {
		if(ObjectUtils.orNull(value, prefix)) {
			return false;
		}
		if(Compares.less(value.length(), prefix.length())) {
			return false;
		}
		if(Compares.isZero(prefix.length())) {
			return true;
		}
		String prefixInValue = value.substring(0, prefix.length());
		return prefixInValue.equalsIgnoreCase(prefix);
	}

	public static boolean endsWithIgnoreCase(String value, String suffix) {
		if(ObjectUtils.orNull(value, suffix)) {
			return false;
		}
		if(Compares.less(value.length(), suffix.length())) {
			return false;
		}
		if(Compares.isZero(suffix.length())) {
			return true;
		}
		String suffixInValue = value.substring(value.length() - suffix.length(), value.length());
		return suffixInValue.equalsIgnoreCase(suffix);
	}
	
	
	public static boolean containsWithIgnoreCase(String value, String sub) {
		if(ObjectUtils.orNull(value, sub)) {
			return false;
		}
		
		if(Compares.less(value.length(), sub.length())) {
			return false;
		}
		return value.toUpperCase().contains(sub.toUpperCase());
	}
	
	public static boolean isChinese(char a) {
	     int v = (int)a;
	     return Compares.notLess(v, 19968) && Compares.notGreater(v, 171941);
	}
	
	
	public static String lowerFirstChar(String value) {
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		char c = Character.toLowerCase(value.charAt(0));
		return c + value.substring(1);
	}
	
	public static String upperFirstChar(String value) {
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		char c = Character.toUpperCase(value.charAt(0));
		return c + value.substring(1);
	}
	
	public static String trimToLength(String value, int length) {
		Preconditions.checkArgument(length > 0, "String length must greater than 0");
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		
		if(Compares.notGreater(value.length(), length)) {
			return value;
		}
		
		return value.substring(0, length) + "...";
	}
	
	/**
	 * 从源字符串中删除指定子字符串，只匹配一次
	 * 如：aaabbbaaaccc.deleteFirst("aaa")，结果为bbbaaaccc
	 * @param value 源字符串
	 * @param toDelete 需要删除子字符串
	 * @return 被删除子字符串的结果字符串
	 */
	public static String deleteFirst(String value, String toDelete) {
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		if(org.apache.commons.lang.StringUtils.isEmpty(toDelete)) {
			return value;
		}
		return value.replaceFirst(toDelete, EMPTY);
	}
	
	/**
	 * 从源字符串中删除指定子字符串，匹配所有
	 * 如：aaabbbaaaccc.deleteFirst("aaa")，结果为bbbccc
	 * @param value 源字符串
	 * @param toDelete 需要删除子字符串
	 * @return 被删除子字符串的结果字符串
	 */
	public static String deleteAll(String value, String toDelete) {
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		if(org.apache.commons.lang.StringUtils.isEmpty(toDelete)) {
			return value;
		}
		return value.replaceAll(toDelete, EMPTY);
	}
}
