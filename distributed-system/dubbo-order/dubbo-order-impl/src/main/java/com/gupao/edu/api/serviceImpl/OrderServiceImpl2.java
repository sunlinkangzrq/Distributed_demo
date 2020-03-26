package com.gupao.edu.api.serviceImpl;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;
import com.gupao.edu.service.IOrderService;

@Service("orderservice2")
public class OrderServiceImpl2 implements IOrderService{

	public OrderResponse doOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("曾将来过 版本2"+request.toString());
		OrderResponse res=new OrderResponse();
		res.setCode(200);
		res.setMsg("success，版本2");
		return res;
	}

}
