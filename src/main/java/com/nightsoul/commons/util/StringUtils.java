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
		return value==null || value.trim().isEmpty();
	}
	
	public static boolean isTrimedNotEmpty(String value) {
		return !isTrimedEmpty(value);
	}
	
	public static String trimLineBreak(String value) {
		return value==null ? value : value.replaceAll(NEWLINE, EMPTY).replaceAll(RETURN, EMPTY);
	}
	
	public static String lineBreakToBr(String value) {
		return value==null ? value : value.replaceAll(RN, "<br/>").replaceAll(NEWLINE, "<br/>");
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
		if(value!=null) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<value.length(); i++) {
				char ch = value.charAt(i);
				if(ch>=19968 && ch<=171941) {
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
		if(value==null || prefix==null) {
			return false;
		}
		if(value.length() < prefix.length()) {
			return false;
		}
		if(prefix.length() == 0) {
			return true;
		}
		String prefixInValue = value.substring(0, prefix.length());
		return prefixInValue.equalsIgnoreCase(prefix);
	}

	public static boolean endsWithIgnoreCase(String value, String suffix) {
		if(value==null || suffix==null) {
			return false;
		}
		if(value.length() < suffix.length()) {
			return false;
		}
		if(suffix.length() == 0) {
			return true;
		}
		String suffixInValue = value.substring(value.length() - suffix.length(), value.length());
		return suffixInValue.equalsIgnoreCase(suffix);
	}
	
	
	public static boolean containsWithIgnoreCase(String value, String sub) {
		if(value==null || sub==null) {
			return false;
		}
		if(value.length() < sub.length()) {
			return false;
		}
		return value.toUpperCase().contains(sub.toUpperCase());
	}
	
	public static boolean isChinese(char a) {
	     int v = (int)a;
	     return (v >=19968 && v <= 171941);
	}
	
	
	public static String lowerFirstChar(String str) {
		if(null==str) {
			return null;
		}
		if(str.length()==0) {
			return str;
		}
		char c = Character.toLowerCase(str.charAt(0));
		return c + str.substring(1);
	}
	
	public static String upperFirstChar(String str) {
		if(null==str) {
			return null;
		}
		if(str.length()==0) {
			return str;
		}
		char c = Character.toUpperCase(str.charAt(0));
		return c + str.substring(1);
	}
	
	public static String toLength(String value, int length) {
		Preconditions.checkArgument(length > 0, "String length must greater than 0");
		if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
			return EMPTY;
		}
		
		if(value.length() <= length) {
			return value;
		}
		
		return value.substring(0, length) + "...";
	}
}
