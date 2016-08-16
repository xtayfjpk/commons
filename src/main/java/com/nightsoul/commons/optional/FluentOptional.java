package com.nightsoul.commons.optional;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class FluentOptional <T> {
	private Optional<T> optional;
	
	private FluentOptional(Optional<T> optional) {
		this.optional = optional;
	}
	
	public static <T> FluentOptional<T> from(Optional<T> optional) {
		return new FluentOptional<T>(optional);
	}
	
	@SuppressWarnings("unchecked")
	public FluentOptional<T> bind(Function<T, Optional<T>> function) {
		if(this.optional.isPresent()) {
			return new FluentOptional<T>(function.apply(this.optional.get()));
		}
		return new FluentOptional<T>((Optional<T>) Optional.absent());
	}
	
	public T get() {
		return this.optional.get();
	}
	
	public T orNull() {
		return this.optional.orNull();
	}
}
