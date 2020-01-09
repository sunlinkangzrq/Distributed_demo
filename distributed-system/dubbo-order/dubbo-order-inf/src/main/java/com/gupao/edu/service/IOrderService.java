package com.gupao.edu.service;

import com.gupao.edu.dto.OrderRequest;
import com.gupao.edu.dto.OrderResponse;

public interface IOrderService {
	
	
	OrderResponse doOrder(OrderRequest request);
}
