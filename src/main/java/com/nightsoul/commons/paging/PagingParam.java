package com.nightsoul.commons.paging;

public class PagingParam <T> {
	private T param;
	private Integer limit;
	private Integer offset;
	
	public PagingParam(T param, Integer limit, Integer offset) {
		this.param = param;
		this.limit = limit;
		this.offset = offset;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	
}
