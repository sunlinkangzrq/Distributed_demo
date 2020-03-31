package com.gupao.edu.inf;

import com.gupao.edu.dto.UserLoginRequest;
import com.gupao.edu.dto.UserLoginResponse;

public interface IUserInf {
	
	public UserLoginResponse login(UserLoginRequest rquest);
}
