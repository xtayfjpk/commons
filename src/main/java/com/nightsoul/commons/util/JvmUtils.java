package com.nightsoul.commons.util;

import java.lang.management.ManagementFactory;


public abstract class JvmUtils {
	private static String pid = null;
	
	
	public static final String getPID() {
		if(pid==null) {
			String name = ManagementFactory.getRuntimeMXBean().getName();
			String[] items = name.split("@");
			pid = items[0];
		}
		return pid;
	}
	
	
}
