package com.gupao.edu.dto;

import java.io.Serializable;

public class OrderRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7669767910450900696L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderRequest [name=" + name + "]";
	}
	
	
}
