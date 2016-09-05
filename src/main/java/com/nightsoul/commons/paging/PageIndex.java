package com.nightsoul.commons.paging;

public class PageIndex {
	private long startIndex;
	private long endIndex;
	
	public PageIndex(long startIndex, long endIndex) 	{
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}
	public long getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * 
	 * @param viewpagecount  页码数量，分页条中显示多少个页码
	 * @param currentPage 当面页
	 * @param totalpage 总页数
	 * @return
	 */
	public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage) {
			long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
			long endpage = currentPage+viewpagecount/2;
			if(startpage<1) {
				startpage = 1;
				if(totalpage>=viewpagecount) 
					endpage = viewpagecount;
				else 
					endpage = totalpage;
			}
			if(endpage>totalpage) {
				endpage = totalpage;
				if((endpage-viewpagecount)>0) 
					startpage = endpage-viewpagecount+1;
				else 
					startpage = 1;
			}
			return new PageIndex(startpage, endpage);		
	}
}
