package com.sxh.zk.demo;

import org.apache.zookeeper.*;

/**
 * Zookeeper观察者，当节点发生变化，打印节点信息
 * @author sxh
 * @date 2021/7/28
 */
public class DataWatcher implements Watcher, Runnable {
    private final static String hostPort = "localhost:2181";
    private final static String zooDataPath = "/test";
    byte[] zoo_data = null;
    ZooKeeper zk;

    public DataWatcher() {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
            // 如果节点不存在，则创建一个节点; 如果节点存在，则为该节点添加watcher
            if (zk.exists(zooDataPath, this) == null) {
                zk.create(zooDataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printData() throws InterruptedException, KeeperException {
        zoo_data = zk.getData(zooDataPath, this, null);
        String zString = new String(zoo_data);
        System.out.printf("\nCurrent Data @ ZK Path %s: %s", zooDataPath, zString);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf(
                "\nEvent Received: %s", event.toString());
        if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                printData();
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args)
            throws InterruptedException, KeeperException {
        DataWatcher dataWatcher = new DataWatcher();
        dataWatcher.printData();
        dataWatcher.run();
    }
}
