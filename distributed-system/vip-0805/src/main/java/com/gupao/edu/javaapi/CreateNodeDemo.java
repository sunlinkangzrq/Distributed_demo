package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

public class CreateNodeDemo {

	private static final  String  CONNECTING="106.54.165.85:2181";
	private static CountDownLatch countDownLatch=new CountDownLatch(1);
	private static ZooKeeper zookeeper=null;
	private static Stat  stat=new Stat();
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		zookeeper=new ZooKeeper(CONNECTING, 5000, new Watcher() {
			
			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getState()==Event.KeeperState.SyncConnected) {
					countDownLatch.countDown();
					System.out.println(arg0.getState());
					if(arg0.getType()==Event.EventType.NodeCreated) {
						try {
							System.out.println("路径："+arg0.getPath()+"->节点的值："+
									zookeeper.getData(arg0.getPath(), true, stat));
						} catch (KeeperException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(arg0.getType()==Event.EventType.NodeDataChanged) {
						try {
							System.out.println("路径："+arg0.getPath()+"->改变后的值："+
									zookeeper.getData(arg0.getPath(), true, stat));
						} catch (KeeperException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(arg0.getType()==Event.EventType.NodeChildrenChanged) {
						
					}else if(arg0.getType()==Event.EventType.NodeDeleted) {
						
					}else if(arg0.getType()==Event.EventType.None) {
						System.out.println("链接上zookeeper");
					}
				}
			}
		});
		countDownLatch.await();
		System.out.println(zookeeper.getState());
//		String result=zookeeper.create("/node", "123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.EPHEMERAL);
//		System.out.println(result);
	}
}
