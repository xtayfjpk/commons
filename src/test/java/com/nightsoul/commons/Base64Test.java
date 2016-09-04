package com.nightsoul.commons;


import org.junit.Test;

import com.nightsoul.commons.util.Base64;

public class Base64Test {

	@Test
	public void test() {
		System.out.println(Base64.encodeBytes("123456".getBytes()));;
	}

}
