package com.gupao.edu.javaapi;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class DistributeLock {
	
	private static final String  RootPaht="/LOCKS";
	private static  ZooKeeper  zookeeper;
	private static  int SESSIONTIMEOUT;
	private String lockId;
	private CountDownLatch  latch=new CountDownLatch(1);
	
	public  DistributeLock() throws IOException, InterruptedException{
		this.zookeeper=ZookeeperClient.getInstance();
		this.SESSIONTIMEOUT=ZookeeperClient.getSESSIONTIMEOUT();
	}
	public  Boolean  lock() {
		try {
			lockId=zookeeper.create(RootPaht+"/", "00".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(Thread.currentThread().getName()+"->成功创建子节点"+lockId+"，开始去竞争锁！");
			List<String> list=zookeeper.getChildren(RootPaht, true);
			SortedSet<String> sort=new TreeSet<String>();
			for(String child:list){
				sort.add(RootPaht+"/"+child);
			}
			String first=sort.first();
			if(lockId.equals(first)){
				System.out.println(Thread.currentThread().getName()+"->成功获得锁，lock节点为:["+lockId+"]");
				return true;
			}
			SortedSet<String> sorted=sort.headSet(lockId);
			if(!sorted.isEmpty()){
				String prevLockID=sorted.last();
				zookeeper.exists(prevLockID, new LockWatcher(latch));
				latch.await(SESSIONTIMEOUT,TimeUnit.MILLISECONDS);
				System.out.println(Thread.currentThread().getName()+"->成功获得锁，lock节点为:["+lockId+"]");
				return true;
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public  Boolean  unlock(){
		System.out.println(Thread.currentThread().getName()+"->开始释放锁:["+lockId+"]");
		try {
			zookeeper.delete(lockId, -1);
			System.out.println("节点["+lockId+"]成功被删除");
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		final CountDownLatch latch=new CountDownLatch(10);
		final Random random=new Random();
		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					DistributeLock lock=null;
	                try {
	                    lock=new DistributeLock();
	                    latch.countDown();
	                    latch.await();
	                    lock.lock();
	                    Thread.sleep(random.nextInt(20));
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }finally {
	                    if(lock!=null){
	                        lock.unlock();
	                    }
	                }
				}
			}).start();
		}
	}
}
