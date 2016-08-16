package com.nightsoul.commons.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageResources {
	private static final ThreadLocal<Locale> localeLocal = new ThreadLocal<Locale>();
	private static final ThreadLocal<String> baseNameLocal = new ThreadLocal<String>();
	
	static {
		baseNameLocal.set("MessageResources");
	}
	
	public static String getMessage(String key) {
		return getMessage(key, null, null);
	}
	

	public static String getMessage(String key, Object... params) {
		return getMessage(key, params, null);
	}
	
	public static String getMessage(String key, Object[] params, String defaultValue) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseNameLocal.get(), getLocale());
		String value = bundle.getString(key);
		if(value==null) {
			value = defaultValue;
		}
		for(int i=0; i<params.length; i++) {
			if(params[i]!=null) {
				value = value.replaceAll("\\{" + i + "\\}", params[i].toString());
			}
		}
	    return value;
	}
	
	private static Locale getLocale() {
		return localeLocal.get() == null ? Locale.getDefault() : localeLocal.get();
	}
	
	public static void setLocale(Locale locale) {
		localeLocal.set(locale);
	}
	
	public static void setBaseName(String baseName) {
		baseNameLocal.set(baseName);
	}
	
}
