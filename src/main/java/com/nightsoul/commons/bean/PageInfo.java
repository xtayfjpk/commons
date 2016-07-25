package com.nightsoul.commons.bean;

public class PageInfo {
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final String ORDERBY_ASC = "Asc";
	public static final String ORDERBY_DESC = "Desc";
	private int pageSize = 10;
	private int totalCount = 0;
	private boolean needCount = true;
	private int totalPage;
	private int pageNum = 1;
	private String orderBy = ORDERBY_ASC;
	private String orderByObj = "";
	private boolean needDistinct = true;

	public boolean isNeedDistinct() {
		return needDistinct;
	}

	public void setNeedDistinct(boolean needDistinct) {
		this.needDistinct = needDistinct;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isNeedCount() {
		return needCount;
	}

	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String by) {
		orderBy = by;
	}

	public String getOrderByObj() {
		return orderByObj;
	}

	public void setOrderByObj(String byObj) {
		orderByObj = byObj;
	}

}
