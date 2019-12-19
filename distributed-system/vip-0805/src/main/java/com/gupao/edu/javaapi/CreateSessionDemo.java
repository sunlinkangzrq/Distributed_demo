package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class CreateSessionDemo {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	private static CountDownLatch countDownLatch=new CountDownLatch(1);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ZooKeeper zookeeper=new ZooKeeper(CONNECTING, 5000, new Watcher() {
			
			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getState()==Event.KeeperState.SyncConnected) {
					countDownLatch.countDown();
					System.out.println(arg0.getState());
				}
			}
		});
		countDownLatch.await();
		System.out.println(zookeeper.getState());
	}
}
