package com.nightsoul.commons;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.nightsoul.commons.util.CollectionUtils;

public class CollectionUtilsTest {

	@Test
	public void testToListSetOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testToListSetOfTPredicateOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testToListTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToListTArrayPredicateOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringListString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringListStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIntegerListString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIntegerListStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToListStringStringFunctionOfStringT() {
		fail("Not yet implemented");
	}

	@Test
	public void testToListStringFunctionOfStringT() {
		List<Integer> list = CollectionUtils.toList("1,2,3", new Function<String, Integer>() {
			@Override
			public Integer apply(String input) {
				return Integer.valueOf(input);
			}
		});
		assertEquals(list.size(), 3);

		List<String> stringList = CollectionUtils.toList("1,2,3", null);
		assertEquals(stringList.size(), 3);
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmpty() {
		fail("Not yet implemented");
	}

}
