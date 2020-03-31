package com.gupao.edu.api.serviceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;
import com.gupao.edu.dto.UserLoginRequest;
import com.gupao.edu.dto.UserLoginResponse;
import com.gupao.edu.inf.IUserInf;
import com.gupao.edu.service.IOrderQueryService;
import com.gupao.edu.service.IOrderService;


@Service("userInf")
public class UserInfImpl  implements  IUserInf{
	

	public void doOrder() {

		IOrderService service = null;

		OrderRequest request = new OrderRequest();
		request.setName("test");
		OrderResponse res = service.doOrder(request);
		System.out.println("订单反馈结果:" + res);

	}
	
	public UserLoginResponse login(UserLoginRequest request) {
		// TODO Auto-generated method stub
		UserLoginResponse response=new  UserLoginResponse();
		if(UserValidator.checkUser(request)) {
			response.setCode("0000");
		}
		response.setCode("0001");
		return response;
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
