package com.sxh.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.UUID;

/**
 * 每隔5s修改一次Zookeeper的节点
 * @author sxh
 * @date 2021/7/28
 */
public class DataUpdater implements Watcher {
    private final static String hostPort = "localhost:2181";
    private final static String zooDataPath = "/test";
    ZooKeeper zk;

    public DataUpdater() {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the znode path /MyConfig every 5 seconds with a new UUID string.
    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            byte[] zoo_data = uuid.getBytes();
            zk.setData(zooDataPath, zoo_data, -1);
            try {
                Thread.sleep(5000); // Sleep for 5 secs
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        DataUpdater dataUpdater = new DataUpdater();
        dataUpdater.run();
    }
}
