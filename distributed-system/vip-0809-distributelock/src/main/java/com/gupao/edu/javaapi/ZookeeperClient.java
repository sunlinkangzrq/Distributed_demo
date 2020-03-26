package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperClient {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	private static  int  SESSIONTIMEOUT=10000;
	private static  CountDownLatch  countdown=new CountDownLatch(1);
	
	public  static  ZooKeeper  getInstance() throws IOException, InterruptedException {
		ZooKeeper zookeeper=new ZooKeeper(CONNECTING,SESSIONTIMEOUT,new Watcher() {
			
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				if(event.getState()==Watcher.Event.KeeperState.SyncConnected) {
					countdown.countDown();
				}
			}
		});
		countdown.await();
		TimeUnit.SECONDS.sleep(5);
		return zookeeper;
	}

	public static int getSESSIONTIMEOUT() {
		return SESSIONTIMEOUT;
	}
}
