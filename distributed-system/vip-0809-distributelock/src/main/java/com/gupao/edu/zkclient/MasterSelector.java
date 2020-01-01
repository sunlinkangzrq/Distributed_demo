package com.gupao.edu.zkclient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

public class MasterSelector {
	private ZkClient  zkClient;
	private IZkDataListener  dataListener;
	private UserServer  server;
	private UserServer  master;
	private  Boolean  isRunning=false;
	private String  Master_Path="/master";
	private ScheduledExecutorService  executorService=Executors.newScheduledThreadPool(1);
	
	public MasterSelector(ZkClient zkClient,UserServer server) {
		super();
		this.zkClient = zkClient;
		this.dataListener = new IZkDataListener() {
			
			public void handleDataDeleted(String arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			public void handleDataChange(String arg0, Object arg1) throws Exception {
				// TODO Auto-generated method stub
				chooseMaster();
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
	
	private  void  chooseMaster(){
		try{
			zkClient.create(Master_Path, server, CreateMode.EPHEMERAL);
			master=server;
			System.out.println(server.getMc_name()+"->获取master，你们都要听从我的安排");
			executorService.schedule(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					releaseMaster();
				}
			}, 5, TimeUnit.SECONDS);
		}catch (ZkNodeExistsException ex) {
			// TODO: handle exception
			UserServer data=zkClient.readData(Master_Path, true);
			if(data==null){
				master=server;
				chooseMaster();
			}else{
				chooseMaster();
			}
		}
	}
	
	private void  releaseMaster(){
		if(checkIsMaster()){
			zkClient.delete(Master_Path);
		}
	}
	
	private Boolean  checkIsMaster(){
		UserServer data=zkClient.readData(Master_Path, true);
		if(data.getMc_name().equals(server.getMc_name())){
			master=server;
			return true;
		}
		return false;
	}
}
