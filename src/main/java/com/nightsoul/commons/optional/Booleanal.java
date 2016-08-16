package com.nightsoul.commons.optional;

public class Booleanal <T> {
	private boolean answer = false;
	private T value;
	
	private Booleanal(boolean answer, T value) {
		this.answer = answer;
		this.value = value;
	};
	
	public static <T> Booleanal<T> of(boolean answer, T value) {
		return new Booleanal<T>(answer, value);
	}


	public static <T> Booleanal<T> ofFalse() {
		return new Booleanal<T>(false, null);
	}

	public static <T> Booleanal<T> ofTrue(T value) {
		return new Booleanal<T>(true, value);
	}
	
	public T get() {
		return this.value;
	}
	
	public boolean isTrue() {
		return answer;
	}
	
	public boolean isFalse() {
		return !isTrue();
	}
}
