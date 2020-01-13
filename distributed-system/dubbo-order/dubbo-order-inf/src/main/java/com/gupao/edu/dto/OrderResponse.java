package com.gupao.edu.dto;

import java.io.Serializable;

public class OrderResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128201366995759679L;
	
	private int code;
	private String msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "OrderResponse [code=" + code + ", msg=" + msg + "]";
	}
	

}
