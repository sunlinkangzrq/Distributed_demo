package com.gupao.edu.zkclient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class SessionDemo {
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	public static void main(String[] args) {
		
		ZkClient zkClient=new ZkClient(CONNECTING,5000);
		
//		zkClient.createEphemeral("/node");
//		zkClient.createPersistent("/temp/temp1/temp1-1/temp1-1-1", true);
		System.out.println("success");
//		List<String> list=zkClient.getChildren("/temp");
//		System.out.println(list);
		zkClient.deleteRecursive("/temp0000000002");
	}
}
