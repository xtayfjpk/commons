package com.nightsoul.commons.jmx.server;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXPrincipal;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * JMX服务类
 * 
 * @see StandardMBean
 * @author zj
 *
 */
public final class MBeanManager {
	private MBeanServer server;
	private JMXConnectorServer jmxConnectorServer;
	
	public MBeanManager() {
		this.server = JMXHelper.lookupMBeanServer();
	}
	
	public void start(final JMXServerConfig config) throws Exception {
		if(jmxConnectorServer!=null && jmxConnectorServer.isActive()) {
		    return;
		}
		String jmxServiceUrl = config.getJmxServiceUrl();
		if(jmxServiceUrl==null || jmxServiceUrl.isEmpty()) {
			throw new JMXException("需要配置JMX服务路径");
		}
		
		if("rmi".equals(config.getProtocol())) {
			if(config.getPort()==null) {
				throw new JMXException("rmi协议需要配置端口");
			}
			LocateRegistry.createRegistry(config.getPort());
		}
		
		JMXServiceURL url = new JMXServiceURL(jmxServiceUrl);
		Map<String, Object> environment = Maps.newHashMap();
		environment.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticator() {
			
			@Override
			public Subject authenticate(Object credentials) {
				if(!(credentials instanceof String[])) {
					throw new SecurityException("Bad credentials");
				}
				String[] creds = (String[])(String[])credentials;
				if(creds.length!=2) {
					throw new SecurityException("Bad credentials");
				}
				String user = creds[0];
				String password = creds[1];
				if(password==null || user==null) {
					throw new SecurityException("Bad password");
				}
				if(user.equals(config.getUserName()) && password.equals(config.getPassword())) {
					Set<JMXPrincipal> principals = Sets.newHashSet();
					principals.add(new JMXPrincipal(user));
					return new Subject(true, principals, Collections.EMPTY_SET, Collections.EMPTY_SET);
				} else {
					throw new SecurityException("Bad password");
				}
			}
		});
		
		JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, environment, server);
		connectorServer.start();
	}

	
	public void registerMBean(String objectName, Object bean) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		ObjectName name = ObjectName.getInstance(objectName);
		this.server.registerMBean(bean, name);
	}
	
	public void unregisterMBean(String objectName) throws MalformedObjectNameException, NullPointerException, MBeanRegistrationException, InstanceNotFoundException {
		ObjectName name = ObjectName.getInstance(objectName);
		this.server.unregisterMBean(name);
	}
	
	public void stop() throws IOException {
		if ((this.jmxConnectorServer != null) && (this.jmxConnectorServer.isActive())) {
			this.jmxConnectorServer.stop();
		}
	}
}
