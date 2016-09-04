package com.nightsoul.commons.jmx.server;

public class JMXServerConfig {
	private Integer port;
	private String jmxServiceUrl;
	private String protocol;
	private String userName;
	private String password;
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getJmxServiceUrl() {
		return jmxServiceUrl;
	}
	public void setJmxServiceUrl(String jmxServiceUrl) {
		this.jmxServiceUrl = jmxServiceUrl;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
