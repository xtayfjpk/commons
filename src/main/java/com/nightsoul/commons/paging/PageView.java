package com.nightsoul.commons.paging;

import java.util.List;

public class PageView<T> {
	/**
	 * 分页数据
	 */
	private List<T>  records;
	/**
	 * 页码开始索引和结束索引
	 */
	private PageIndex  pageIndex;
	/**
	 * 总页数
	 */
	private long totalPage=1;
	/**
	 * 每页显示记录数，一页显示多少记录
	 */
	private int maxResult=10;
	/**
	 * 当前页
	 */
	private int currentPage=1;
	/**
	 * 总记录数
	 */
	private long totalRecord;
	/**
	 * 页码数量，分页条中显示多少个页码
	 */
	private long pageCode=10;
	
	public PageView(int maxResult, int currentPage) {
		this.maxResult = maxResult;
		this.currentPage = currentPage;
	}
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records)  {
		this.records = records;
	}
	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
		this.pageIndex=PageIndex.getPageIndex(pageCode, currentPage, totalPage);
	}
	public int getMaxResult() {
		return maxResult;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecord() 	{
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		setTotalPage(this.totalRecord%this.maxResult==0  ?  this.totalRecord/this.maxResult  :  this.totalRecord/this.maxResult+1);
	}
	
	@SuppressWarnings("unchecked")
	public void setQueryResult(@SuppressWarnings("rawtypes") QueryResult  qr) {
		this.setTotalRecord(qr.getTotalRecord());
		this.setRecords(qr.getResultList());
	}

	public long getPageCode() {
		return pageCode;
	}

	public void setPageCode(long pageCode) {
		this.pageCode = pageCode;
	}
	
	/**
	 * 计算开始索引
	 * @return
	 */
	public  int  getFirstIndex() {
		return  (currentPage-1) * maxResult;
	}
}
