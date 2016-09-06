package com.nightsoul.commons.util;



public abstract class Compares {
	private Compares(){}
	
	public static boolean greater(char left, char right) {
		return left > right;
	}
	public static boolean greater(byte left, byte right) {
		return left > right;
	}
	public static boolean greater(short left, short right) {
		return left > right;
	}
	public static boolean greater(float left, float right) {
		return left > right;
	}
	public static boolean greater(int left, int right) {
		return left > right;
	}
	public static boolean greater(long left, long right) {
		return left > right;
	}
	public static boolean greater(double left, double right) {
		return left > right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean greater(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) > 0;
	}
	

	public static boolean notGreater(char left, char right) {
		return left <= right;
	}
	public static boolean notGreater(byte left, byte right) {
		return left <= right;
	}
	public static boolean notGreater(short left, short right) {
		return left <= right;
	}
	public static boolean notGreater(float left, float right) {
		return left <= right;
	}
	public static boolean notGreater(int left, int right) {
		return left <= right;
	}
	public static boolean notGreater(long left, long right) {
		return left <= right;
	}
	public static boolean notGreater(double left, double right) {
		return left <= right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean notGreater(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) <= 0;
	}

	public static boolean less(char left, char right) {
		return left < right;
	}
	public static boolean less(byte left, byte right) {
		return left < right;
	}
	public static boolean less(short left, short right) {
		return left < right;
	}
	public static boolean less(float left, float right) {
		return left < right;
	}
	public static boolean less(int left, int right) {
		return left < right;
	}
	public static boolean less(long left, long right) {
		return left < right;
	}
	public static boolean less(double left, double right) {
		return left < right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean less(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) < 0;
	}

	public static boolean notLess(char left, char right) {
		return left >= right;
	}
	public static boolean notLess(byte left, byte right) {
		return left >= right;
	}
	public static boolean notLess(short left, short right) {
		return left >= right;
	}
	public static boolean notLess(float left, float right) {
		return left >= right;
	}
	public static boolean notLess(int left, int right) {
		return left >= right;
	}
	public static boolean notLess(long left, long right) {
		return left >= right;
	}
	public static boolean notLess(double left, double right) {
		return left >= right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean notLess(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) >= 0;
	}

	public static boolean equals(char left, char right) {
		return left == right;
	}
	public static boolean equals(byte left, byte right) {
		return left == right;
	}
	public static boolean equals(short left, short right) {
		return left == right;
	}
	public static boolean equals(float left, float right) {
		return left == right;
	}
	public static boolean equals(int left, int right) {
		return left == right;
	}
	public static boolean equals(long left, long right) {
		return left == right;
	}
	public static boolean equals(double left, double right) {
		return left == right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean equals(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) == 0;
	}

	public static boolean notEquals(char left, char right) {
		return left != right;
	}
	public static boolean notEquals(byte left, byte right) {
		return left != right;
	}
	public static boolean notEquals(short left, short right) {
		return left != right;
	}
	public static boolean notEquals(float left, float right) {
		return left != right;
	}
	public static boolean notEquals(int left, int right) {
		return left != right;
	}
	public static boolean notEquals(long left, long right) {
		return left != right;
	}
	public static boolean notEquals(double left, double right) {
		return left != right;
	}
	@SuppressWarnings("unchecked")
	public static <T> boolean notEquals(Comparable<T> left, Comparable<T> right) {
		return left.compareTo((T) right) != 0;
	}
	
	public static boolean isZero(char value) {
		return value == 0;
	}
	public static boolean isZero(byte value) {
		return value == 0;
	}
	public static boolean isZero(short value) {
		return value == 0;
	}
	public static boolean isZero(float value) {
		return value == 0.0f;
	}
	public static boolean isZero(int value) {
		return value == 0;
	}
	public static boolean isZero(long value) {
		return value == 0;
	}
	public static boolean isZero(double value) {
		return value == 0.0;
	}

	public static boolean isNotZero(char value) {
		return !isZero(value);
	}
	public static boolean isNotZero(byte value) {
		return !isZero(value);
	}
	public static boolean isNotZero(short value) {
		return !isZero(value);
	}
	public static boolean isNotZero(float value) {
		return !isZero(value);
	}
	public static boolean isNotZero(int value) {
		return !isZero(value);
	}
	public static boolean isNotZero(long value) {
		return !isZero(value);
	}
	public static boolean isNotZero(double value) {
		return !isZero(value);
	}

	public static boolean greaterThanZero(char value) {
		return value > 0;
	}
	public static boolean greaterThanZero(byte value) {
		return value > 0;
	}
	public static boolean greaterThanZero(short value) {
		return value > 0;
	}
	public static boolean greaterThanZero(float value) {
		return value > 0;
	}
	public static boolean greaterThanZero(int value) {
		return value > 0;
	}
	public static boolean greaterThanZero(long value) {
		return value > 0;
	}
	public static boolean greaterThanZero(double value) {
		return value > 0;
	}

	public static boolean lessThanZero(char value) {
		return value < 0;
	}
	public static boolean lessThanZero(byte value) {
		return value < 0;
	}
	public static boolean lessThanZero(short value) {
		return value < 0;
	}
	public static boolean lessThanZero(float value) {
		return value < 0;
	}
	public static boolean lessThanZero(int value) {
		return value < 0;
	}
	public static boolean lessThanZero(long value) {
		return value < 0;
	}
	public static boolean lessThanZero(double value) {
		return value < 0;
	}

	public static boolean notLessThanZero(char value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(byte value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(short value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(float value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(int value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(long value) {
		return !lessThanZero(value);
	}
	public static boolean notLessThanZero(double value) {
		return !lessThanZero(value);
	}

	public static boolean notGreaterThanZero(char value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(byte value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(short value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(float value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(int value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(long value) {
		return !greaterThanZero(value);
	}
	public static boolean notGreaterThanZero(double value) {
		return !greaterThanZero(value);
	}

	public static boolean isNegativeOne(char value) {
		return value == -1;
	}
	public static boolean isNegativeOne(byte value) {
		return value == -1;
	}
	public static boolean isNegativeOne(short value) {
		return value == -1;
	}
	public static boolean isNegativeOne(float value) {
		return value == -1;
	}
	public static boolean isNegativeOne(int value) {
		return value == -1;
	}
	public static boolean isNegativeOne(long value) {
		return value == -1;
	}
	public static boolean isNegativeOne(double value) {
		return value == -1;
	}

	public static boolean isNotNegativeOne(char value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(byte value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(short value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(float value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(int value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(long value) {
		return !isNegativeOne(value);
	}
	public static boolean isNotNegativeOne(double value) {
		return !isNegativeOne(value);
	}

	
	
	
}
