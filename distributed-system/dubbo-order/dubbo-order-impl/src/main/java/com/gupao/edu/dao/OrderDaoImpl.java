package com.gupao.edu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDaoImpl implements OrderDao {

	@Autowired
	JdbcTemplate  orderJdbcTemplate;
	
	public void insertOrder() {
		// TODO Auto-generated method stub
		orderJdbcTemplate.execute("insert order(status,price,order_time) values(1,10,now())");
	}

}
