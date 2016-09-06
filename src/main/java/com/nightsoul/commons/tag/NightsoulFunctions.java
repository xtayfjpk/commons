package com.nightsoul.commons.tag;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Splitter;
import com.nightsoul.commons.util.ObjectUtils;

/**
 * 取值函数
 * @author zjin
 *
 */
public class NightsoulFunctions {
	
	/**
	 * 根据以“.”分隔的key从某对象上获取值，先取对象属性(property)，如果属性不存在，则取字段(field)，
	 * 如果值不存在，或出现异常则返回null
	 * @param target 目标对象
	 * @param chainedKey “.”分隔的key
	 * @param force 是否使用暴力反射
	 * @return
	 */
	public static Object getValue(boolean force, Object target, String chainedKey) {
		if(ObjectUtils.isNull(target)) {
			return null;
		}
		if(StringUtils.isEmpty(chainedKey)) {
			return target;
		}
	
		Iterable<String> keys = Splitter.on('.').split(chainedKey);
		for(String key : keys) {
			target = getCurrentValue(force, target, key);
			if(ObjectUtils.isNull(target)) {
				break;
			}
		}
		return target;
	}

	
	public static Object getValue(Object target, String chainedKey) {
		return getValue(true, target, chainedKey);
	}
	
	/**
	 * 获取当前值：在chainedKey遍历过程中，当前key的值
	 * @param target 目标对象
	 * @param key 可能为Map的key，也可能是属性名称，也可能是字段名称，优先级如序
	 * @param force 否使用暴力反射
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Object getCurrentValue(boolean force, Object target, String key) {
		if(target instanceof Map) {
			return ((Map) target).get(key);
		} else {
			Object value = null;
			try {
				value = getPropertyValue(force, target, key);
				if(ObjectUtils.isNull(value)) {
					value = getDeclaredFieldValue(force, target, key);
				}
				if(ObjectUtils.isNull(value)) {
					value = getFieldValue(force, target, key);
				}
			} catch (Exception e) {
				// ignore
			}
			return value;
		}
	}
	
	/**
	 * 获取对象属性值
	 */
	private static Object getPropertyValue(boolean force, Object bean, String propName) throws Exception {
		PropertyDescriptor[] pds = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
		if(ObjectUtils.isNotNull(pds)) {
			for(PropertyDescriptor pd : pds) {
				if(pd.getName().equals(propName)) {
					Method method = pd.getReadMethod();
					if(force) {
						method.setAccessible(true);
					}
					// method.isAccessible()为什么返回false,又可以调用
					return method.invoke(bean);
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取该对象所在类声明的字段值
	 */
	private static Object getDeclaredFieldValue(boolean force, Object bean, String fieldName) throws Exception {
		Field field = bean.getClass().getDeclaredField(fieldName);
		return internalGetFieldValue(force, bean, field);
	}

	/**
	 * 获取该对象的public或从interface继承而来的字段值
	 */
	private static Object getFieldValue(boolean force, Object bean, String fieldName) throws Exception {
		Field field = bean.getClass().getField(fieldName);
		return internalGetFieldValue(force, bean, field);
	}

	/**
	 * 获取该对象某字段值
	 */
	private static Object internalGetFieldValue(boolean force, Object bean, Field field) throws Exception {
		if(ObjectUtils.isNotNull(field)) {
			if(force) {
				field.setAccessible(true);
			}
			return field.isAccessible() ? field.get(bean) : null;
		}
		return null;
	}
	
	
	public static Object invoke(Object bean, String methodName) {
		return invoke(bean, null, methodName);
	}
	public static Object invoke(Object bean, String methodName, Object firstArg) {
		return invoke(bean, null, methodName, firstArg);
	}
	public static Object invoke(Object bean, String methodName, Object firstArg, Object secondArg) {
		return invoke(bean, null, methodName, firstArg, secondArg);
	}
	public static Object invoke(Object bean, String methodName, Object firstArg, Object secondArg, Object thirdArg) {
		return invoke(bean, null, methodName, firstArg, secondArg, thirdArg);
	}

	public static Object invoke(Object bean, String methodName, Object... args) {
		return invoke(bean, null, methodName, args);
	}
	
	
	public static Object invoke(Object bean, String chainedKey, String methodName) {
		return invoke(bean, chainedKey, methodName, new Object[0]);
	}
	public static Object invoke(Object bean, String chainedKey, String methodName, Object firstArg) {
		return invoke(bean, chainedKey, methodName, new Object[]{firstArg});
	}
	public static Object invoke(Object bean, String chainedKey, String methodName, Object firstArg, Object secondArg) {
		return invoke(bean, chainedKey, methodName, new Object[]{firstArg, secondArg});
	}
	public static Object invoke(Object bean, String chainedKey, String methodName, Object firstArg, Object secondArg, Object thirdArg) {
		return invoke(bean, chainedKey, methodName, new Object[]{firstArg, secondArg, thirdArg});
	}
	
	public static Object invoke(Object bean, String chainedKey, String methodName, Object... args) {
		return invoke(true, bean, chainedKey, methodName, args);
	}
	
	/**
	 * 调用某对象方法，并使用该方法返回值作为该函数返回值，如果出现异常则返回null
	 * @param force 是否使用暴力反射
	 * @param bean 目标bean
	 * @param chainedKey chainedKey “.”分隔的key
	 * @param methodName 方法名称
	 * @param args 方法参数
	 * @return
	 */
	public static Object invoke(boolean force, Object bean, String chainedKey, String methodName, Object... args) {
		bean = getValue(bean, chainedKey);
		if(ObjectUtils.isNull(bean)) {
			return null;
		}
		Object value = null;
		try {
			value = invokeDeclaredMethod(force, bean, methodName, args);
			if(ObjectUtils.isNull(value)) {
				value = invokeMethod(force, bean, methodName, args);
			}
		} catch (Exception e) {
			// ignore
		}
		return value;
	}

	public static Object invoke(boolean force, Object bean, String chainedKey, String methodName) {
		bean = getValue(bean, chainedKey);
		if(ObjectUtils.isNull(bean)) {
			return null;
		}
		Object value = null;
		try {
			value = invokeDeclaredMethod(force, bean, methodName);
			if(ObjectUtils.isNull(value)) {
				value = invokeMethod(force, bean, methodName);
			}
		} catch (Exception e) {
			// ignore
		}
		return value;
	}
	
	private static Object invokeDeclaredMethod(boolean force, Object bean, String methodName, Object... args) throws Exception {
		Class<?>[] parameterTypes = new Class<?>[args.length];
		Method method = bean.getClass().getDeclaredMethod(methodName, parameterTypes);
		return internalInvokeMethod(force, bean, method, args);
	}

	private static Object invokeMethod(boolean force, Object bean, String methodName, Object... args) throws Exception {
		Class<?>[] parameterTypes = new Class<?>[args.length];
		Method method = bean.getClass().getMethod(methodName, parameterTypes);
		return internalInvokeMethod(force, bean, method, args);
	}
	
	private static Object internalInvokeMethod(boolean force, Object bean, Method method, Object... args) throws Exception {
		if(ObjectUtils.isNotNull(method)) {
			if(force) {
				method.setAccessible(true);
			}
			return method.isAccessible() ? method.invoke(bean, args) : null;
		}
		return null;
	}
	
	
	/*public static void main(String[] args) throws Exception {
		AjaxResult ajaxResult = new AjaxResult(true, "成功");
		Map<String, Object> data = Maps.newHashMap();
		data.put("result", new AjaxResult(false, "失败"));
		ajaxResult.setData(data);
		System.out.println(getValue(ajaxResult, "data.result.msg"));
		//System.out.println(ajaxResult.getClass().getDeclaredField("msg").get(ajaxResult));
		
		System.out.println(invoke(ajaxResult, "data.result", "getMsg"));
	}*/
}
