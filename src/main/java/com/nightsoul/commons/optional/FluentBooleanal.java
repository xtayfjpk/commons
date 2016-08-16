package com.nightsoul.commons.optional;

import com.google.common.base.Function;

public class FluentBooleanal <T> {
	private Booleanal<T> booleanal;
	
	private FluentBooleanal(Booleanal<T> booleanal) {
		this.booleanal = booleanal;
	}
	
	public static <T> FluentBooleanal<T> from(Booleanal<T> optional) {
		return new FluentBooleanal<T>(optional);
	}
	
	@SuppressWarnings("unchecked")
	public FluentBooleanal<T> bind(Function<T, Booleanal<T>> function) {
		if(this.booleanal.isTrue()) {
			return new FluentBooleanal<T>(function.apply(this.booleanal.get()));
		}
		return new FluentBooleanal<T>((Booleanal<T>) Booleanal.ofFalse());
	}

	@SuppressWarnings("unchecked")
	public FluentBooleanal<T> bind(Function<T, Booleanal<T>> trueFunction, Function<T, Booleanal<T>> falseFunction) {
		if(this.booleanal.isTrue()) {
			return new FluentBooleanal<T>(trueFunction.apply(this.booleanal.get()));
		} else {
			falseFunction.apply(this.booleanal.get());
			return new FluentBooleanal<T>((Booleanal<T>) Booleanal.ofFalse());
		}
	}
	
	public boolean isTrue() {
		return this.booleanal.isTrue();
	}

	public boolean isFalse() {
		return this.booleanal.isFalse();
	}
	
	public T get() {
		return this.booleanal.get();
	}
	
}
