package com.nightsoul.commons;


import org.junit.Test;

import com.nightsoul.commons.util.MD5Utils;

public class MD5Test {

	@Test
	public void test() {
		System.out.println(MD5Utils.getMD5("zaq1XSW2".getBytes()));;
	}

}
