package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 异步任务执行处理类
 * @author sxh
 * @date 2021/11/15
 */
public class _01_Future {
    /**
     * CompletableFuture出现之前使用的方法 -> Future
     */
    @Test
    public void onlyFuture() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> future = es.submit(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread();
            return 100;
        });
        // 查看future异步任务执行结果，两个办法：
        // 1.get()方法阻塞调用线程，直到异步线程执行完毕返回结果
        // Integer result = future.get();

        // 2.轮询调用isDone()，查看是否为true
        while (true) {
            if (future.isDone()) {
                Util.printTimeAndThread("异步任务执行成功！");
                break;
            }
        }
    }
}
