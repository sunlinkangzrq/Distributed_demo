package com.gupao.edu.service;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;

public class UserService {
	
	public void  doOrder() {
		
		IOrderService service=null;
		
		OrderRequest  request=new OrderRequest();
		request.setName("test");
		OrderResponse res=service.doOrder(request);
		System.out.println("订单反馈结果:"+res);
		
	}
	public static void main(String[] args) {
		ClassPathXmlApplicationContext  context=new ClassPathXmlApplicationContext("consumer.xml");
		IOrderService service=(IOrderService) context.getBean("orderservice");
		
		OrderRequest  request=new OrderRequest();
		request.setName("test");
		OrderResponse res=service.doOrder(request);
		System.out.println("订单反馈结果:"+res);
	}
}
