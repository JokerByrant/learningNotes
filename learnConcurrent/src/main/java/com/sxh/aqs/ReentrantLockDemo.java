package com.sxh.aqs;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock：独占锁
 * 包括：公平锁、非公平锁
 *
 * @author sxh
 * @date 2021/6/8
 */
public class ReentrantLockDemo {
    @Test
    public void fun() {
        // 创建一个公平锁：线程在加锁时直接尝试获取锁，获取不到就会到等待队列的队尾等待。吞吐效率高，但是线程可能会因为长时间获取不到锁而饿死。
        ReentrantLock fairLock = new ReentrantLock(true);
        // 创建一个非公平锁：多个线程按照申请锁的顺序获取锁。吞吐效率相对非公平锁低，但是线程不会出现饿死的情况。
        ReentrantLock noFairLock = new ReentrantLock();

        // ReentrantLock是可重入锁，同一线程可以多次加锁，加了多少次锁在释放锁时就要释放多少次
        noFairLock.lock();
        noFairLock.lock();
        noFairLock.unlock();
        noFairLock.unlock();

//        fairLock.lock();
//        fairLock.unlock();
    }
}
