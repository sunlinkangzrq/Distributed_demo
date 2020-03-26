package com.gupao.edu.api.serviceImpl;

import org.springframework.stereotype.Service;

import com.gupao.edu.service.IOrderQueryService;


@Service("orderqueryservice")
public class OrderQueryServiceImpl implements IOrderQueryService {

	public String doQuery(String name) {
		// TODO Auto-generated method stub
		System.out.println("hello "+name);
		return "hello "+name;
	}

}
