package com.sxh.zk.demo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 示例程序，连接Zookeeper服务，打印"/"下的所有节点名
 *
 * @author sxh
 * @date 2021/7/28
 */
public class FirstDemo {
    private final static String host = "127.0.0.1:2181";
    private final static int timeout = 2000;

    public static void main(String[] args) throws IOException {
        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = new ZooKeeper(host, timeout, null);
        try {
            zooChildren = zk.getChildren(zpath, false);
            System.out.println("Znodes of '/': ");
            for (String child : zooChildren) {
                //print the children
                System.out.println(child);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
