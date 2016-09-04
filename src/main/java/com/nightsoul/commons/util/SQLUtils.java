package com.nightsoul.commons.util;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public abstract class SQLUtils {
	private SQLUtils(){}
	
	public static String buildInSQL(String paramName, List<? extends Serializable> paramValues) {
		return buildInSQL(paramName, paramValues.toArray(new Serializable[0]));
	}

	public static String buildNotInSQL(String paramName, List<? extends Serializable> paramValues) {
		return buildInSQL(true, paramName, paramValues.toArray(new Serializable[0]));
	}
	
	public static String buildInSQL(String paramName, Serializable... paramValues) {
		return buildInSQL(false, paramName, paramValues);
	}

	public static String buildNotInSQL(String paramName, Serializable... paramValues) {
		return buildInSQL(true, paramName, paramValues);
	}
	
	public static String buildLikeSQL(String paramName, String value) {
		return StringUtils.SPACE + paramName + "like '%" + value + "%' ";
	}

	public static String buildNotLikeSQL(String paramName, String value) {
		return StringUtils.SPACE + paramName + "not like '%" + value + "%' ";
	}

	public static String buildEqualSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + "=" + getValueSQL(value);
	}
	public static String buildNotEqualSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + "!=" + getValueSQL(value);
	}

	public static String buildGreaterThanSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + ">" + getValueSQL(value);
	}
	public static String buildNotGreaterThanSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + "<=" + getValueSQL(value);
	}
	
	public static String buildLessThanSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + "<" + getValueSQL(value);
	}
	public static String buildNotLessThanSQL(String paramName, Serializable value) {
		return StringUtils.SPACE + paramName + ">=" + getValueSQL(value);
	}

	public static String buildBetweenSQL(String paramName, Serializable lower, Serializable upper) {
		return buildNotLessThanSQL(paramName, lower) + " and" + buildNotGreaterThanSQL(paramName, upper);
	}

	public static String getValueSQL(Serializable value) {
		return value instanceof String ? "'" + value + "'" : value.toString();
	}
	
	private static String buildInSQL(boolean isNotIn, String paramName, Serializable... paramValues) {
		if (paramValues == null || paramValues.length == 0) {
			return StringUtils.EMPTY;
		}
		StringBuilder builder = new StringBuilder(StringUtils.SPACE);
		builder.append(paramName);
		if(isNotIn) {
			builder.append(" not");
		}
		builder.append(" in (");
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
		return Lists.partition(values, splitSize);
	}
}
