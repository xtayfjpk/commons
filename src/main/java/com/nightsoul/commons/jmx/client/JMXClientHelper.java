package com.nightsoul.commons.jmx.client;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

import org.apache.commons.beanutils.BeanUtils;

import com.nightsoul.commons.jmx.server.JMXException;
import com.nightsoul.commons.util.ObjectValue;
import com.nightsoul.commons.util.ReflectUtils;
import com.nightsoul.commons.util.StringUtils;

//已完成
public abstract class JMXClientHelper {
	
	public static <T> T newMBeanProxy(MBeanServerConnection connection, String objectName, Class<T> clazz, boolean notificationBroadcaster) {
		ObjectName name = null;
		try {
			name = ObjectName.getInstance(objectName);
		} catch (Exception e) {
			throw new JMXException("ObjectName初始化失败" + e.getMessage(), e);
		}
		T proxy = MBeanServerInvocationHandler.newProxyInstance(connection, name, clazz, notificationBroadcaster);
		return proxy;
	}
	
	public static <T> T newMBeanProxy(MBeanServerConnection connection, String objectName, Class<T> clazz) {
		return newMBeanProxy(connection, objectName, clazz, true);
	}
	
	public static Object invokeRemoteMethod(MBeanServerConnection connection, String objectName, String operationName, Object[] params, String[] signature) {
		Object value = null;
		ObjectName name = null;
		try {
			name = ObjectName.getInstance(objectName);
		} catch (Exception e) {
			throw new JMXException(String.format("%s初始化失败，%s", objectName, e.getMessage()), e);
		}
		try {
			value = connection.invoke(name, operationName, params, signature);
		} catch (Exception e) {
			throw new JMXException("获取远程Mbean失败：" + e.getMessage(), e);
		}
		return value;
	}
	
	public static Object getAttribute(MBeanServerConnection connection, String objectName, String attribute) {
		Object value = null;
		ObjectName name = null;
		try {
			name = ObjectName.getInstance(objectName);
		} catch (Exception e) {
			throw new JMXException(String.format("%s初始化失败，%s", objectName, e.getMessage()), e);
		}
		try {
			value = connection.getAttribute(name, attribute);
		} catch (Exception e) {
			throw new JMXException("获取属性值失败：" + e.getMessage(), e);
		}
		return value;
	}
	
	public static Map<String, Object> getAttributes(MBeanServerConnection connection, String objectName, String[] attributes) {
		ObjectName name = null;
		try {
			name = ObjectName.getInstance(objectName);
		} catch (Exception e) {
			throw new JMXException(String.format("%s初始化失败，%s", objectName, e.getMessage()), e);
		}
		Map<String, Object> results = new HashMap<String, Object>();
		try {
			AttributeList attributeList = connection.getAttributes(name, attributes);
			for(Attribute attr : attributeList.asList()) {
				if(attr.getValue()==null) {
					results.put(attr.getName(), attr.getValue());
				} else if ((attr.getValue() instanceof Short) || (attr.getValue() instanceof Boolean) || (attr.getValue() instanceof Void) || (attr.getValue() instanceof Integer) || (attr.getValue() instanceof Byte) || (attr.getValue() instanceof Long) || (attr.getValue() instanceof Character) || (attr.getValue() instanceof Float) || (attr.getValue() instanceof String) || (attr.getValue() instanceof Double) || (attr.getValue() instanceof BigDecimal) || (attr.getValue() instanceof BigInteger) || (attr.getValue() instanceof Date) || (attr.getValue() instanceof ObjectName)) {
					results.put(attr.getName(), attr.getValue());
				} else if(attr.getValue() instanceof CompositeData) {
					CompositeData data = (CompositeData) attr.getValue();
					Iterator<String> iter = data.getCompositeType().keySet().iterator();
					Map<String, Object> temp = new HashMap<String, Object>();
					while(iter.hasNext()) {
						String key = iter.next();
						temp.put(key, data.get(key));
					}
					results.put(attr.getName(), temp);
				} else if(attr.getValue() instanceof TabularData) {
					TabularData data = (TabularData) attr.getValue();
					List<Object> tempList = new ArrayList<Object>();
					Iterator<?> iter = data.values().iterator();
					while(iter.hasNext()) {
						Map<String, Object> tempMap = new HashMap<String, Object>();
						CompositeData compositeData = (CompositeData)iter.next();
						Iterator<String> compositeDataIter = compositeData.getCompositeType().keySet().iterator();
						while(compositeDataIter.hasNext()) {
							String key = compositeDataIter.next();
							tempMap.put(key, compositeData.get(key));
							tempList.add(tempMap);
						}
					}
					results.put(attr.getName(), tempList);
				} else if(attr.getValue() instanceof Object[]) {
					results.put(attr.getName(), attr.getValue());
				} else {
					new JMXException("未能识别的类型:" + attr.getValue().getClass().getName());
				}
			}
		} catch (Exception e) {
			throw new JMXException("获取属性值失败：" + e.getMessage(), e);
		}
		return results;
	}
	
	@SuppressWarnings("rawtypes")
	public static void populateAttributes(MBeanServerConnection connection, String objectName, String[] attributes, Object bean) {
		Map<String, Object> values = getAttributes(connection, objectName, attributes);
		Iterator<Map.Entry<String, Object>> iter = values.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			try {
				Field field = bean.getClass().getDeclaredField(StringUtils.lowerFirstChar((String)entry.getKey()));
				if (entry.getValue() == null) {
					
					BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String)entry.getKey()), null);
				} else if (field.getType().equals(String.class)) {
					BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String)entry.getKey()), ObjectValue.getString(entry.getValue()));
				} else if (field.getType().equals(Integer.class)) {
					BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String)entry.getKey()), ObjectValue.getInteger(entry.getValue()));
				} else if (field.getType().equals(Double.class)) {
					BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String)entry.getKey()), ObjectValue.getDouble(entry.getValue()));
				} else if (field.getType().equals(Date.class)) {
					BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String)entry.getKey()), ObjectValue.getDate(entry.getValue()));
				} else if(field.getType().equals(Map.class) || ReflectUtils.isImplementInterface(field.getType(), Map.class)) {
					Object value = entry.getValue();
					if (value instanceof List) {
						List<?> list = (List<?>) value;
						if ((list != null) && (list.size() > 0) && (list.get(0) instanceof Map)) {
							Map<Object, Object> result = new HashMap<Object, Object>();
							Iterator listIter = list.iterator();
							while (listIter.hasNext()) {
								Map map = (Map) listIter.next();
								for(Object key : map.keySet()) {
									result.put(key, map.get(key));
								}
							}
							BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String) entry.getKey()), result);
						}
					}
				} else if (ReflectUtils.isImplementInterface(field.getType(), List.class)) {
					if (entry.getValue() instanceof Object[]) {
						Object value = entry.getValue();
						List<Object> list = JMXDataHelper.arrayToList((Object[]) value);
						BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String) entry.getKey()), list);
					} else {
						BeanUtils.setProperty(bean, StringUtils.lowerFirstChar((String) entry.getKey()), entry.getValue());
					}
				}
			} catch (Exception e) {
				throw new JMXException(e.getMessage(), e);
			}
		}
	}
	
	
	public static MBeanInfo getMBeanInfo(MBeanServerConnection connection,String objectName) {
		MBeanInfo mBeanInfo;
		ObjectName name = null;
		try {
			name = ObjectName.getInstance(objectName);
		} catch (Exception e) {
			throw new JMXException(String.format("%s初始化失败，%s", objectName, e.getMessage()), e);
		}

		try {
			mBeanInfo = connection.getMBeanInfo(name);
		} catch (Exception e) {
			throw new JMXException("获取MbeanInfo失败：" + e.getMessage(), e);
		}
		return mBeanInfo;
	}
}
