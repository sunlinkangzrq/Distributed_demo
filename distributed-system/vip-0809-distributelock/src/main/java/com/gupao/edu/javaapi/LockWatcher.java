package com.gupao.edu.javaapi;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class LockWatcher  implements Watcher {
	private  CountDownLatch  countdown=null;

	
	
	public LockWatcher(CountDownLatch countdown) {
		this.countdown = countdown;
	}



	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		if(event.getState()==Watcher.Event.KeeperState.SyncConnected){
			if(event.getType()==Event.EventType.NodeDeleted){				
				countdown.countDown();
			}
		}
	}

}
