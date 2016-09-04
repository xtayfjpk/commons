package com.nightsoul.commons.jmx.client;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.nightsoul.commons.jmx.server.JMXException;
import com.nightsoul.commons.jmx.server.JMXServerConfig;

public abstract class MBeanServerConnectionHelper {
	private MBeanServerConnectionHelper() {}
	
	public static MBeanServerConnection getConnection(String jmxServiceUrl, Map<String, ?> env) {
		try {
			JMXServiceURL url = new JMXServiceURL(jmxServiceUrl);
			JMXConnector jmxConnector = JMXConnectorFactory.connect(url, env);
			MBeanServerConnection serverConnection = jmxConnector.getMBeanServerConnection();
			return serverConnection;
		} catch (Exception e) {
			throw new JMXException(e.getMessage(), e);
		}
	}
	
	
	public MBeanServerConnection getConnection(JMXServerConfig config) {
		try {
			String url = config.getJmxServiceUrl();
			JMXServiceURL jmxServiceURL = new JMXServiceURL(url);
			Map<String, Object> environment = new HashMap<String, Object>();
			String[] credentials = {config.getUserName(), config.getPassword()};
			environment.put("jmx.remote.credentials", credentials);
			JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, environment);
			return jmxConnector.getMBeanServerConnection();
		} catch (Exception e) {
			throw new JMXException(e.getMessage(), e);
		} 
	}
}
