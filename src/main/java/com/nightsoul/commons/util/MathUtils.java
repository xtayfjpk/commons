package com.nightsoul.commons.util;


public abstract class MathUtils {
	private MathUtils(){}
	
	public static int floor(double value) {
		long longValue = (long)Math.floor(value);
		return Long.valueOf(longValue).intValue();
	}

	public static int ceil(double value) {
		long longValue = (long)Math.ceil(value);
		return Long.valueOf(longValue).intValue();
	}

	public static int round(double value) {
		long longValue = Math.round(value);
		return Long.valueOf(longValue).intValue();
	}
	
	public static boolean isZero(int value) {
		return value == 0;
	}

	public static boolean isZero(double value) {
		return value == 0.0;
	}

	public static boolean isZero(float value) {
		return value == 0.0f;
	}

	public static boolean isZero(long value) {
		return value == 0L;
	}
}
