package com.gupao.edu.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorOperateDemo {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	public static void main(String[] args) {
		CuratorFramework  curatorFramework=CuratorUtils.getInstance();
		System.out.println("success");
		
		
	}
}
