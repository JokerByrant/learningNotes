package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务任意结果消费
 * 注：下面的几个方法中，任务都是并行执行的
 * @author sxh
 * @date 2021/11/16
 */
public class _05_Either_Consume {
    /**
     * applyToEither() -> 其中任意一个任务执行完毕后，执行方法体，入参为对应任务的返回值，返回新结果。执行的两个任务返回值要一样。
     */
    @Test
    public void _applyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Method A";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread("processing b...");
            return "Method B";
        });
        CompletableFuture<String> cfAB = cfA.applyToEither(cfB, (method) -> "结果:" + method);
        // 返回：Method A
        Util.printTimeAndThread(cfAB.get());
        // 等待Method B结束
        Util.sleep(2000);
    }

    /**
     * acceptEither() -> 其中任任意一个任务完成后，执行方法体，消费结果，入参为已完成的任务结果。无返回值，要求两个任务结果为同一类型。
     */
    @Test
    public void _acceptEither() {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Method A";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread("processing b...");
            return "Method B";
        });
        CompletableFuture<Void> cfAB = cfA.acceptEither(cfB, (method) -> Util.printTimeAndThread("结果：" + method));
        // 返回：Method A
        cfAB.join();
        // 等待Method B结束
        Util.sleep(2000);
    }

    /**
     * runAfterEither() -> 其中任意一个任务完成后，执行Runnable，消费结果，无入参。不返回新结果，不要求两个任务结果为同一类型
     */
    @Test
    public void _runAfterEither() {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Method A";
        });
        CompletableFuture<Object> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread("processing b...");
            return "Method B";
        });
        CompletableFuture<Void> cfAB = cfA.runAfterEither(cfB, () -> Util.printTimeAndThread("线程信息与Method A一致！"));
        // 返回：Method A
        cfAB.join();
        // 等待Method B结束
        Util.sleep(2000);
    }
}
