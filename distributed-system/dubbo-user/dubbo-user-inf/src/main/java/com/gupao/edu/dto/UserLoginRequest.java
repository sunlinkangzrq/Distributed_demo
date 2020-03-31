package com.gupao.edu.dto;

import java.io.Serializable;

public class UserLoginRequest implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 708860100238652910L;

	private String username;
	
	private String  password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
