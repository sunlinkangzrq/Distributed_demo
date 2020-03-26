package com.gupao.edu.curator;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.jboss.netty.util.internal.ExecutorUtil;

public class CuratorOperateDemo {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	public static void main(String[] args) {
		CuratorFramework  curatorFramework=CuratorUtils.getInstance();
		System.out.println("success");
		/**
		 * 创建子节点操作
		 */
		
//		try { 
//			String result=curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
//		  		.forPath("/curator/curator1-1/curator1-1-1","123".getBytes());
//		 	System.out.println("创建成功："+result); 
//		} catch (Exception e) { // TODO Auto-generated
//			 e.printStackTrace(); 
//		}
		 
		/**
		 * 修改操作
		 * 
		 */
//		try {
//			Stat stat=curatorFramework.setData().forPath("/curator","222".getBytes());
//			System.out.println("节点数据修改后的内容："+stat);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		/**
		 * 获取数据操作
		 */
//		try {
//			Stat stat=new Stat();
//			byte[] result=curatorFramework.getData().storingStatIn(stat).forPath("/curator");
//			System.out.println("result:"+new String(result)+";stat:"+stat);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		/**
		 * 删除操作
		 */
//		try {
//			curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
//			System.out.println("delete成功");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*
		 * 异步操作
		 * 
		 */
//		try {
//			ExecutorService service=Executors.newSingleThreadExecutor();
//			final CountDownLatch countdown=new CountDownLatch(1);
//			String result=curatorFramework.create().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
//				
//				public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//					// TODO Auto-generated method stub
//					System.out.println("异步调用结果："+client.getState()+"->eventCode:"+event.getResultCode()+event.getType());
//					countdown.countDown();
//				}
//			},service).forPath("/mic");
//			countdown.await();
//			service.shutdown();
//			System.out.println("返回的结果："+result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/**
		 * 事务操作
		 */
		try {
			Collection<CuratorTransactionResult> trasations=(Collection<CuratorTransactionResult>) curatorFramework.inTransaction().create().forPath("/node")
					.and().setData().forPath("/mic","123".getBytes()).and().commit();
			for(CuratorTransactionResult result:trasations) {
				System.out.println(result.getForPath()+"->"+result.getType());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
