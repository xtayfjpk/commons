package com.nightsoul.commons.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLUtils {
	private SQLUtils(){}
	
	public static String buildInSQL(String paramName, List<? extends Serializable> paramValues) {
		return buildInSQL(paramName, paramValues.toArray(new Serializable[0]));
	}
	
	public static String buildInSQL(String paramName, Serializable... paramValues) {
		if (paramValues == null || paramValues.length == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder(" ");
		builder.append(paramName).append(" in (");
		for (Serializable paramValue : paramValues) {
			if (paramValue instanceof String) {
				builder.append("'");
			}
			builder.append(paramValue);
			if (paramValue instanceof String) {
				builder.append("'");
			}
			builder.append(",");
		}

		if (builder.length() > 0) {
			builder.setLength(builder.length() - 1);
		}
		builder.append(")");

		return builder.toString();
	}
	
	/**
	 * 切分IN查询值，因为Hibernate在进行in查询时，如果查询值过多会导致异常，所以需要对其进行切分
	 * 
	 * 为什么此方法放置在HqlUtil类中ant编译不能通过，而在此类中却没问题，是类中不同jar中的原因么？？？？？
	 * @param values
	 * @param splitSize 分片大小
	 * @return
	 */
	public static <T> List<List<T>> splitInSQLValues(List<T> values, int splitSize) {
		List<List<T>> subValuesList = new ArrayList<List<T>>();
		List<T> subValues = new ArrayList<T>();
		for (int i = 1; i <= values.size(); i++) {
			if (i % splitSize == 0) {
				subValuesList.add(subValues);
				subValues = new ArrayList<T>();
			}
			subValues.add(values.get(i - 1));
		}
		if (!subValues.isEmpty()) {
			subValuesList.add(subValues);
		}
		return subValuesList;
	}
}
