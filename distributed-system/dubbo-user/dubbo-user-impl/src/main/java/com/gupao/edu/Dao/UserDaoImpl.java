package com.gupao.edu.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

	@Autowired
    JdbcTemplate userJdbcTemplate;
	
	public void updateUser() {
		// TODO Auto-generated method stub
		userJdbcTemplate.execute("update user set name='mic' ,  sex=0 , mobile=13958254786");
	}

}
