package com.sxh.demo;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用
 *
 * @author sxh
 * @date 2021/6/28
 */
public class CountDownLatchDemo {
    @Test
    public void fun1() throws InterruptedException {
        int count = 10;
        CountDownLatch cdl = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            getNewThread().start();
            cdl.countDown();
        }
        System.out.println(Thread.currentThread().getName());
        cdl.await();
        Thread.currentThread().join();
    }

    private Thread getNewThread() {
        return new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }

    /**
     * 1. 利用CountDown实现监控List大小
     * 这种方式不会阻塞线程，调用countDown()后，当前线程继续执行
     */
    @Test
    public void fun2() {
        LinkedList<Integer> list = new LinkedList<>();
        CountDownLatch cdl = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2 start");
            if (list.size() != 5) {
                try {
                    // 阻塞当前线程
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end");
            }
        },"t2").start();

        new Thread(() ->{
            System.out.println("t1 start");
            for (int i = 1; i <= 10; i++) {
                list.add(i);
                System.out.println("add " + i);
                if (list.size() == 5) {
                    System.out.println("execute countDown");
                    // 操作唤醒了t2
                    cdl.countDown();
                }
            }
        }, "t1").start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. 利用notify()、wait()实现监控List大小
     * notify()唤醒别的线程-不会释放锁，wait()阻塞当前线程-会释放锁。
     * 使用这种方式切换线程会涉及线程的阻塞，当调用notify()-wait()后，线程会阻塞
     */
    @Test
    public void fun3() {
        LinkedList<Integer> list = new LinkedList<>();
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 start");
                if (list.size() != 5) {
                    try {
                        // 阻塞当前t2
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 end");
                }
                // 唤醒t1
                lock.notify();
            }
        },"t2").start();

        new Thread(() ->{
            synchronized (lock) {
                System.out.println("t1 start");
                for (int i = 1; i <= 10; i++) {
                    list.add(i);
                    System.out.println("add " + i);
                    if (list.size() == 5) {
                        // 唤醒t2
                        lock.notify();
                        try {
                            // 阻塞当前t1
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}
