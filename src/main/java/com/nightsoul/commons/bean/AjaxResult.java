package com.nightsoul.commons.bean;


public class AjaxResult {
	private boolean success;
	private String msg;
	private Object data;
	
	
	public AjaxResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
