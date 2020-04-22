package com.gupao.edu.inf;

import com.gupao.edu.dto.DebitRequest;
import com.gupao.edu.dto.DebitResponse;
import com.gupao.edu.dto.UserLoginRequest;
import com.gupao.edu.dto.UserLoginResponse;

public interface IUserInf {
	
	public UserLoginResponse login(UserLoginRequest rquest);
	
	/*
     * 更新用户余额
     */
    DebitResponse debit(DebitRequest request);
}
