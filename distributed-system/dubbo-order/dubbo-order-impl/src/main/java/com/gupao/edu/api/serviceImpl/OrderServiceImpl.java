package com.gupao.edu.api.serviceImpl;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.gupao.edu.dao.OrderDao;
import com.gupao.edu.dto.DebitRequest;
import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;
import com.gupao.edu.inf.IUserInf;
import com.gupao.edu.service.IOrderService;


@Service(value="orderservice")
public class OrderServiceImpl implements IOrderService{

	@Autowired
	OrderDao  orderDao;
	
	@Autowired
	JtaTransactionManager springTransactionManager;
	
	@Autowired
	IUserInf  userInf;
	
	
	
	
	public OrderResponse doOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		System.out.println("曾将来过"+request.toString());
		UserTransaction userTransaction=springTransactionManager.getUserTransaction();
		try {
			userTransaction.begin();
			orderDao.insertOrder();
			userInf.debit(new DebitRequest());
			userTransaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				userTransaction.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		OrderResponse res=new OrderResponse();
		res.setCode(200);
		res.setMsg("success");
		return res;
	}

}
