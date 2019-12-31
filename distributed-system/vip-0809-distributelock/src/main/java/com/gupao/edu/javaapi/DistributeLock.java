package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class DistributeLock {
	
	private static String  RootPaht="/LOCKS";
	
	public  Boolean  lock() {
		try {
			ZooKeeper zookeeper=ZookeeperClient.getInstance();
			String lockId=zookeeper.create(RootPaht+"/", "00".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			List<String> list=zookeeper.getChildren(RootPaht, true);
			Set<String> sort=new TreeSet<String>();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
