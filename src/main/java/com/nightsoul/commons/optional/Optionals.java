package com.nightsoul.commons.optional;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class Optionals {
	
	public static <T> Optional<T> bind(Optional<T> optional, Function<T, Optional<T>> function) {
		if(optional.isPresent()) {
			return function.apply(optional.get());
		}
		return Optional.absent();
	}
}
