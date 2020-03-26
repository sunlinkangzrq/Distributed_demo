package com.gupao.edu.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorUtils {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	public static  CuratorFramework  getInstance() {
		CuratorFramework  curatorFramework=CuratorFrameworkFactory
				.newClient(CONNECTING, 5000, 10000, new ExponentialBackoffRetry(1000, 3));
		curatorFramework.start();
		return curatorFramework;
	}
}
