package com.gupao.edu.api.serviceImpl;

import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;
import com.gupao.edu.service.IOrderService;

public class OrderServiceImpl implements IOrderService{

	public OrderResponse doOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		System.out.println("曾将来过"+request.toString());
		OrderResponse res=new OrderResponse();
		res.setCode(200);
		res.setMsg("success");
		return res;
	}

}
