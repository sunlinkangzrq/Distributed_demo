package com.gupao.edu.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;

public class CuratorEventDemo {
	
	private static final  String  CONNECTING="106.54.165.85:2181";
	
	/**
	 * 三种watcher来做节点的监听
     * pathcache   监视一个路径下子节点的创建、删除、节点数据更新
     * NodeCache   监视一个节点的创建、更新、删除
     * TreeCache   pathcaceh+nodecache 的合体（监视路径下的创建、更新、删除事件），
               * 缓存路径下的所有子节点的数据
	 * @throws Exception 
     */
	public static void main(String[] args) throws Exception {
		CuratorFramework curator=CuratorUtils.getInstance();
		String path="/event";
		/*
		 * 监控节点创建、修改删除操作
		 */
//		curator.create().withMode(CreateMode.EPHEMERAL).forPath(path, "123".getBytes());
//		NodeCache  nodecache=new NodeCache(curator,path);
//		nodecache.start();
//		nodecache.getListenable().addListener(()-> System.out.println("节点后发生变化，变化后的数据："+new String(nodecache.getCurrentData().getData())));
//		curator.setData().forPath(path, "333".getBytes());
//		TimeUnit.SECONDS.sleep(2);
		/*
		 * 监控子节点创建、修改、删除操作
		 */
		PathChildrenCache  cache=new PathChildrenCache(curator, path, true);
		cache.start();
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				// TODO Auto-generated method stub
				switch(event.getType()) {
					case CHILD_ADDED:
						System.out.println("新增子节点");
						break;
					case  CHILD_REMOVED:
						System.out.println("删除子节点");
						break;
					case CHILD_UPDATED:
						System.out.println("更新子节点");
						break;
					default:break;
				}
			}
		});
		curator.create().withMode(CreateMode.PERSISTENT).forPath(path, "kangkang".getBytes());
		curator.create().withMode(CreateMode.EPHEMERAL).forPath(path+"/event1","222".getBytes());
		TimeUnit.SECONDS.sleep(1);
		curator.setData().forPath(path+"/event1", "111".getBytes());
		TimeUnit.SECONDS.sleep(1);
		curator.delete().forPath(path+"/event1");
	}

}
