package com.nightsoul.commons.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PagingData<T> {
	private int totalCount;
	private String identifier = "id";
	private String label = "name";
	
	private List<T> items;
	private PageInfo pageInfo;
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public PagingData(){
		
	}
	
	
	
	public int getTotalCount() {
		return totalCount > items.size() ? totalCount : items.size();
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("totalCount", this.totalCount);
		map.put("identifier",this.identifier);
		map.put("label",this.label);
		map.put("items", this.items);
		map.put("pageInfo", this.pageInfo);
		return map;
	}

	
}
