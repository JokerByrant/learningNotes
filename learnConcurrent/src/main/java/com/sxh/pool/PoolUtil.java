package com.sxh.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池Demo
 *
 * @author sxh
 * @date 2021/5/31
 */
public class PoolUtil {
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }
}
