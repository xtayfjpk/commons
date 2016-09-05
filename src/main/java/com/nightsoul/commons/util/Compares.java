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
	
}
