package com.nightsoul.commons.util;

import static org.junit.Assert.*;
import static com.nightsoul.commons.util.StringUtils.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testIsTrimedEmpty() {
		String value = null;
		assertTrue(isTrimedEmpty(value));

		value = " ";
		assertTrue(isTrimedEmpty(value));
		
		value = "value ";
		assertFalse(isTrimedEmpty(value));
	}

	@Test
	public void testIsTrimedNotEmpty() {
		String value = null;
		assertFalse(isTrimedNotEmpty(value));

		value = " ";
		assertFalse(isTrimedNotEmpty(value));
		
		value = "value ";
		assertTrue(isTrimedNotEmpty(value));
	}

	@Test
	public void testTrimLineBreak() {
		String value = trimLineBreak("foo\r\n\n\r");
		assertEquals(value, "foo");
	}

	@Test
	public void testLineBreakToBr() {
		String value = lineBreakToBr("foo\r\n");
		assertEquals(value, "foo<br/>");
	}

	@Test
	public void testAscii2native() {
		String value = ascii2native("\u4e2d\u56fd");
		assertEquals("中国", value);
	}

	@Test
	public void testNative2ascii() {
		String value = native2ascii("中国");
		assertEquals("\\u4e2d\\u56fd", value);
	}

	@Test
	public void testStartsWithIgnoreCase() {
		String value = "Abcdefg";
		assertTrue(startsWithIgnoreCase(value, "aBc"));
	}

	@Test
	public void testEndsWithIgnoreCase() {
		String value = "Abcdefg";
		assertTrue(endsWithIgnoreCase(value, "EfG"));
	}

	@Test
	public void testContainsWithIgnoreCase() {
		String value = "Abcdefg";
		assertTrue(containsWithIgnoreCase(value, "EfG"));
	}

	@Test
	public void testIsChinese() {
		assertTrue(isChinese('中'));
		assertFalse(isChinese('a'));
	}

	@Test
	public void testLowerFirstChar() {
		assertEquals(lowerFirstChar("Abc"), "abc");
	}

	@Test
	public void testUpperFirstChar() {
		assertEquals(upperFirstChar("abc"), "Abc");
	}

	@Test
	public void testTrimToLength() {
		String value = trimToLength("abcdefg", 3);
		assertEquals(value, "abc...");
	}

	@Test
	public void testDeleteFirst() {
		assertEquals(deleteFirst("aaabbbaaaccc", "aaa"), "bbbaaaccc");
	}

	@Test
	public void testDeleteAll() {
		assertEquals(deleteAll("aaabbbaaaccc", "aaa"), "bbbccc");
	}

}
