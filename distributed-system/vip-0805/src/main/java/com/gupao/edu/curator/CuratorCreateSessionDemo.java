package com.gupao.edu.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorCreateSessionDemo {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	public static void main(String[] args) {
		CuratorFramework  curatorFramework=CuratorFrameworkFactory
				.newClient(CONNECTING, 5000, 5000, new ExponentialBackoffRetry(1000, 3));
		curatorFramework.start();
		CuratorFramework  curatorFramework1=CuratorFrameworkFactory.builder().connectString(CONNECTING)
											.sessionTimeoutMs(5000).connectionTimeoutMs(5000)
											.retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
		curatorFramework1.start();
		System.out.println("success");
		
	}
}
