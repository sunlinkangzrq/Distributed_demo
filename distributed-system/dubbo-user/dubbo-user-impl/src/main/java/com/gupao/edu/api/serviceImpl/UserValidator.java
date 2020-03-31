package com.gupao.edu.api.serviceImpl;

import com.gupao.edu.dto.UserLoginRequest;

public class UserValidator {
	
	public static Boolean  checkUser(UserLoginRequest request) {
		Boolean  flag=true;
		if(request.getUsername().isEmpty()||request.getPassword().isEmpty()) {
			flag=false;
		}
		return  flag;
	}
}
