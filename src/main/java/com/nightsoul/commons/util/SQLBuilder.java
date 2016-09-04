package com.nightsoul.commons.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class SQLBuilder implements Appendable {
	private StringBuilder sqlBuilder = new StringBuilder();
	
	private SQLBuilder() {}
	private SQLBuilder(String sql) {
		this.sqlBuilder.append(sql);
	}
	
	public static SQLBuilder create() {
		return new SQLBuilder();
	}
	public static SQLBuilder create(String sql) {
		return new SQLBuilder(sql);
	}

	@Override
	public SQLBuilder append(CharSequence sql) {
		this.sqlBuilder.append(sql);
		return this;
	}

	@Override
	public SQLBuilder append(CharSequence sql, int start, int end) {
		this.sqlBuilder.append(sql, start, end);
		return this;
	}

	@Override
	public SQLBuilder append(char value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}

	public SQLBuilder append(boolean value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(byte value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(short value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(int value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(long value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(float value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(double value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	public SQLBuilder append(Object value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}

	public SQLBuilder append(StringBuffer value) throws IOException {
		this.sqlBuilder.append(value);
		return this;
	}
	
	
	public SQLBuilder appendInSQL(String paramName, List<? extends Serializable> paramValues) {
		this.sqlBuilder.append(SQLUtils.buildInSQL(paramName, paramValues));
		return this;
	}
	
	public SQLBuilder appendNotInSQL(String paramName, List<? extends Serializable> paramValues) {
		this.sqlBuilder.append(SQLUtils.buildNotInSQL(paramName, paramValues));
		return this;
	}
	
	public SQLBuilder appendInSQL(String paramName, Serializable... paramValues) {
		this.sqlBuilder.append(SQLUtils.buildInSQL(paramName, paramValues));
		return this;
	}
	
	public SQLBuilder appendNotInSQL(String paramName, Serializable... paramValues) {
		this.sqlBuilder.append(SQLUtils.buildNotInSQL(paramName, paramValues));
		return this;
	}
	
	public SQLBuilder appendLikeSQL(String paramName, String value) {
		this.sqlBuilder.append(SQLUtils.buildLikeSQL(paramName, value));
		return this;
	}

	public SQLBuilder appendNotLikeSQL(String paramName, String value) {
		this.sqlBuilder.append(SQLUtils.buildNotLikeSQL(paramName, value));
		return this;
	}
	
	public SQLBuilder appendEqualSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildEqualSQL(paramName, value));
		return this;
	}

	public SQLBuilder appendNotEqualSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildNotEqualSQL(paramName, value));
		return this;
	}

	public SQLBuilder appendGreaterThanSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildGreaterThanSQL(paramName, value));
		return this;
	}

	public SQLBuilder appendNotGreaterThanSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildNotGreaterThanSQL(paramName, value));
		return this;
	}

	public SQLBuilder appendLessThanSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildLessThanSQL(paramName, value));
		return this;
	}
	
	public SQLBuilder appendNotLessThanSQL(String paramName, Serializable value) {
		this.sqlBuilder.append(SQLUtils.buildNotLessThanSQL(paramName, value));
		return this;
	}
	
	public SQLBuilder appendBetweenSQL(String paramName, Serializable lower, Serializable upper) {
		this.sqlBuilder.append(SQLUtils.buildBetweenSQL(paramName, lower, upper));
		return this;
	}
	

	@Override
	public String toString() {
		return this.sqlBuilder.toString();
	}
}
