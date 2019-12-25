package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

public class AuthControlDemo implements  Watcher {
	private static final  String  CONNECTING="106.54.165.85:2181";
	private static CountDownLatch countDownLatch=new CountDownLatch(1);
	private static ZooKeeper zookeeper=null;
	private static Stat  stat=new Stat();
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		zookeeper=new ZooKeeper(CONNECTING, 5000, new AuthControlDemo());
		countDownLatch.await();
		ACL  acl=new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
		List<ACL>  list=new ArrayList<ACL>();
		list.add(acl);
		zookeeper.create("/temp", "123".getBytes(), list, CreateMode.PERSISTENT);
//		ZooKeeper zookeeper1=new ZooKeeper(CONNECTING, 5000, new AuthControlDemo());
//		TimeUnit.SECONDS.sleep(2);
//		zookeeper1.delete("/temp", -1);
	}
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getState()==Event.KeeperState.SyncConnected) {
			countDownLatch.countDown();
			System.out.println(arg0.getState());
		}
	}
	
}
