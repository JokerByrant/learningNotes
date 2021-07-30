package com.sxh.zk.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author sxh
 * @date 2021/7/30
 */
public class ZkWatcher implements Watcher {

    private static final Logger log = LoggerFactory.getLogger(ZkWatcher.class);

    //异步锁
    private CountDownLatch cdl;

    //标记
    private String mark;

    public ZkWatcher(CountDownLatch cdl,String mark) {
        this.cdl = cdl;
        this.mark = mark;
    }

    //监听事件处理方法
    public void process(WatchedEvent event) {
        log.info(mark+" watcher监听事件：{}",event);
        cdl.countDown();
    }

}
