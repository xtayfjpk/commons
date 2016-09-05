package com.nightsoul.commons.jmx.server;

public class JMXException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public JMXException(){}
	
	public JMXException(String message) {
		super(message);
	}
	
	public JMXException(String message, Throwable t) {
		super(message, t);
	}
}
