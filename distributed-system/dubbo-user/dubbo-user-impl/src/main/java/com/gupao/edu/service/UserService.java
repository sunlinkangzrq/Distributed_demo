package com.gupao.edu.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;

public class UserService {

	public void doOrder() {

		IOrderService service = null;

		OrderRequest request = new OrderRequest();
		request.setName("test");
		OrderResponse res = service.doOrder(request);
		System.out.println("订单反馈结果:" + res);

	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/order-consumer.xml");
		IOrderQueryService query = (IOrderQueryService) context.getBean("orderqueryservice");
		System.out.println(query.doQuery("mic"));
		IOrderService service=(IOrderService) context.getBean("orderservice");
		/*
		 * try {
		 * OrderRequest request=new OrderRequest(); request.setName("test");
		 * service.doOrder(request); Future<IOrderService>
		 * future=RpcContext.getContext().getFuture(); System.out.println(22222);
		 * OrderResponse res1=(OrderResponse) future.get(); System.out.println(res1); }
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		OrderRequest request = new OrderRequest();
		request.setName("test");
		OrderResponse res = service.doOrder(request);
		System.out.println("订单反馈结果:" + res);
	}
}
