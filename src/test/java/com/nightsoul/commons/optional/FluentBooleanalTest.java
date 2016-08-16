package com.nightsoul.commons.optional;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Function;

public class FluentBooleanalTest {

	@Test
	public void test() {
		boolean answer = FluentBooleanal.from(Booleanal.ofTrue(""))
				.bind(firstLegal())
				.bind(secondLegal())
				.bind(thirdLegal(), falseHandler())
				.isTrue();
		assertFalse(answer);
	}

	
	public static Function<String, Booleanal<String>> firstLegal() {
		return new Function<String, Booleanal<String>>() {

			@Override
			public Booleanal<String> apply(String input) {
				System.out.println("first true, value:" + input);
				return Booleanal.ofTrue("中国");
			}
		};
	}

	public static Function<String, Booleanal<String>> secondLegal() {
		return new Function<String, Booleanal<String>>() {
			
			@Override
			public Booleanal<String> apply(String input) {
				System.out.println("second false, value:" + input);
				return Booleanal.ofFalse();
			}
		};
	}
	
	public static Function<String, Booleanal<String>> thirdLegal() {
		return new Function<String, Booleanal<String>>() {
			
			@Override
			public Booleanal<String> apply(String input) {
				System.out.println("third true, value:" + input);
				return Booleanal.ofTrue("美国");
			}
		};
	}
	
	public static Function<String, Booleanal<String>> falseHandler() {
		return new Function<String, Booleanal<String>>() {

			@Override
			public Booleanal<String> apply(String input) {
				System.out.println("false handler");
				return Booleanal.ofFalse();
			}
		};
	}
}
