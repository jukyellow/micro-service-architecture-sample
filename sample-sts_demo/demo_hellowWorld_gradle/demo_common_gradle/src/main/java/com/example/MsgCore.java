package com.example;

public class MsgCore {
	private String apiId;
	private String msg;
	
	public MsgCore() {
		
	}
	public MsgCore(String apiId, String msg) {
		this.apiId = apiId;
		this.msg = msg;
	}
	
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
