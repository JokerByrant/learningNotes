package com.sxh;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * 使用volatile在非一写多读的情况下会出现数据不一致的情况
 * 这里开启两个线程同时对其进行增和减操作
 * @author sxh
 * @date 2020/8/26
 */
public class VolatileExample {
    public static volatile int count = 0; // 计数器
    public static final int size = 100000; // 循环测试次数

    public static void main(String[] args) {
        // ++ 方式 10w 次
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= size; i++) {
                count++;
            }
        });
        thread.start();
        // -- 10w 次
        for (int i = 1; i <= size; i++) {
            count--;
        }
        // 等所有线程执行完成
        while (thread.isAlive()) {}
        System.out.println(count); // 打印结果
    }

    @Benchmark
    public void measureName() {
    }
}

