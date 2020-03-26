package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

public class ApiOperateDemo implements Watcher{

	private static final  String  CONNECTING="106.54.165.85:2181";
	private static CountDownLatch countDownLatch=new CountDownLatch(1);
	private static ZooKeeper zookeeper=null;
	private static Stat  stat=new Stat();
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		zookeeper=new ZooKeeper(CONNECTING, 5000, new ApiOperateDemo());
		countDownLatch.await();
		System.out.println(zookeeper.getState());
//		String result=zookeeper.create("/node", "123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.EPHEMERAL);
//		zookeeper.getData("/node",true,stat);
//		System.out.println(result);
//		zookeeper.setData("/node", "23".getBytes(), -1);
//		Thread.sleep(2000);
//		zookeeper.delete("/node", -1);
//		Thread.sleep(2000);
		
		
		String path="/micsss";
		Stat stat=zookeeper.exists(path, true);
		if(stat==null){
			zookeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			TimeUnit.SECONDS.sleep(1);
		}
		zookeeper.create(path+"/mic1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		TimeUnit.SECONDS.sleep(1);
		//子节点修改
		zookeeper.setData(path+"/mic1", "23".getBytes(), -1);
		TimeUnit.SECONDS.sleep(1);
		//删除子节点
		zookeeper.delete(path+"/mic1", -1);
		TimeUnit.SECONDS.sleep(1);
		zookeeper.delete(path, -1);
		TimeUnit.SECONDS.sleep(1);
		
	}

	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if(arg0.getState()==Event.KeeperState.SyncConnected) {
			countDownLatch.countDown();
			System.out.println(arg0.getState());
			if(arg0.getType()==Event.EventType.NodeCreated) {
				try {
					System.out.println("创建路径："+arg0.getPath()+"->节点的值："+
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
					System.out.println("修改节点路径："+arg0.getPath()+"->改变后的值："+
							zookeeper.getData(arg0.getPath(), true, stat));
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(arg0.getType()==Event.EventType.NodeChildrenChanged) {
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
			}else if(arg0.getType()==Event.EventType.NodeDeleted) {
				System.out.println("节点"+arg0.getPath()+"被删除了");
			}else if(arg0.getType()==Event.EventType.None&&null==arg0.getPath()) {
				System.out.println("链接上zookeeper");
			}
		}
	
	}
}
