package com.nightsoul.commons.optional;

import com.google.common.base.Function;

public class Booleanals {
	
	public static  Booleanal<String> bind(Booleanal<String> booleanal, Function<String, Booleanal<String>> function) {
		if(booleanal.isTrue()) {
			return function.apply(booleanal.get());
		}
		return Booleanal.ofFalse();
	}
	
	
}
