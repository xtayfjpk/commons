package com.nightsoul.commons.jmx.server;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

public abstract class JMXHelper {
	private JMXHelper() {}
	
	public static MBeanServer lookupMBeanServer() {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		return server==null ? MBeanServerFactory.createMBeanServer() : server;
	}
}
