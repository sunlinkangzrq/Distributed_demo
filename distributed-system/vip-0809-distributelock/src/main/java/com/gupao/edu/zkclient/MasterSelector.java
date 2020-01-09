package com.gupao.edu.zkclient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

public class MasterSelector {
	private static ZkClient  zkClient;
	private IZkDataListener  dataListener;
	private UserCenter  server;
	private UserCenter  master;
	private  Boolean  isRunning=false;
	private String  Master_Path="/master";
	private ScheduledExecutorService  executorService=Executors.newScheduledThreadPool(1);
	
	public MasterSelector(ZkClient zkClient,UserCenter server) {
		System.out.println("["+server+"] 去争抢master权限");
		this.zkClient = zkClient;
		this.dataListener = new IZkDataListener() {
			
			public void handleDataDeleted(String arg0) throws Exception {
				// TODO Auto-generated method stub
				chooseMaster();
			}
			
			public void handleDataChange(String arg0, Object arg1) throws Exception {
				// TODO Auto-generated method stub
			}
		};
		this.server = server;
	}

	public  void  start(){
		if(!isRunning){
			isRunning=true;
			zkClient.subscribeDataChanges(Master_Path, dataListener);
			chooseMaster();
		}
	}
	
	public void stop(){
		if(isRunning){
			isRunning=false;
			zkClient.unsubscribeDataChanges(Master_Path, dataListener);
			releaseMaster();
			executorService.shutdown();
		}
	}
	
	public  void  chooseMaster(){
		if(!isRunning) {
			System.out.println("当前服务未启动");
			return;
		}
		try{
			zkClient.createEphemeral(Master_Path, server);
			master=server;
			System.out.println(server.getMc_name()+"->获取master，你们都要听从我的安排");
			executorService.schedule(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					releaseMaster();
				}
			}, 2, TimeUnit.SECONDS);
		}catch (ZkNodeExistsException ex) {
			// TODO: handle exception
			UserCenter data=zkClient.readData(Master_Path, true);
			if(data==null){
				System.out.println("再次启动");
				chooseMaster();
			}else{
				master=data;
			}
		}
	}
	
	private void  releaseMaster(){
		if(checkIsMaster()){
			zkClient.delete(Master_Path);
			System.out.println(master.getMc_name()+"->master被删除");
		}
	}
	
	private Boolean  checkIsMaster(){
		UserCenter data=zkClient.readData(Master_Path, true);
		if(data.getMc_name().equals(server.getMc_name())){
			master=server;
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		final CountDownLatch  countdown=new CountDownLatch(1);
		final String  CONNECTING="106.54.165.85:2181";
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					try {
						countdown.countDown();
						UserCenter  server=new UserCenter();
						server.setMc_id(1);
						server.setMc_name("mc_2");
						System.out.println(server);
						countdown.await();
						ZkClient  zkclient=new ZkClient(CONNECTING,15000);
						MasterSelector  selector=new MasterSelector(zkclient, server);
						selector.start();
						Thread.sleep(1000);
						while(true) {
							Thread.sleep(10000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
	}
}
