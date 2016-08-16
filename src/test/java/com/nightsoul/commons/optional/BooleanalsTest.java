package com.nightsoul.commons.optional;

import static org.junit.Assert.*;
import static com.nightsoul.commons.optional.Booleanals.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.google.common.base.Function;

public class BooleanalsTest {

	@Test
	public void test() {
		boolean answer = bind(bind(bind(Booleanal.ofTrue((String)null), firstLegal()), secondLegal()), thirdLegal()).isTrue();
		assertThat(answer, CoreMatchers.is(false));
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
}
