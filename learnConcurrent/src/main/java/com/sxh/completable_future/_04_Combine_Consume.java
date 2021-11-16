package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 异步任务结果合并消费
 * 注：下面三个方法中合并消费的几个任务都是并行执行的
 * @author sxh
 * @date 2021/11/15
 */
public class _04_Combine_Consume {
    /**
     * thenCombine() -> 合并另外一个任务，两个任务都完成后，执行方法体。入参为两个任务结果，返回新结果。
     */
    @Test
    public void _thenCombine() {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.printTimeAndThread("processing a...");
            return "Drink";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            return " Milk";
        });
        CompletableFuture<String> cfC = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing c...");
            return " And Eat Bread";
        });
        // 上面三个任务是异步执行的，相互之间不阻塞
        CompletableFuture<String> cfABC = cfA.thenCombine(cfB, (resultA, resultB) -> {
            Util.printTimeAndThread(resultA + resultB);  // Drink Milk
            return resultA + resultB;
        }).thenCombine(cfC, (resultAB, resultC) -> {
            Util.printTimeAndThread(resultAB + resultC); // Drink Milk And Eat Bread
            return resultAB + resultC;
        });
        cfABC.join();
    }

    /**
     * thenAcceptBoth() -> 合并另外一个任务，两个任务都完成后，执行方法体。入参为两个任务结果，无返回值。
     */
    @Test
    public void _thenAcceptBoth() {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Hello";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            return " World";
        });
        // 两个任务异步执行
        CompletableFuture<Void> cfAB = cfA.thenAcceptBoth(cfB, (resultA, resultB) -> {
            Util.printTimeAndThread(resultA + resultB);
        });
        cfAB.join();
    }

    /**
     * runAfterBoth() -> 合并另外一个任务，两个任务都完成后，执行方法体，无入参，无返回值。
     */
    @Test
    public void _runAfterBoth() {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Hello";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            return " World";
        });
        // 两个任务异步执行
        CompletableFuture<Void> cfAB = cfA.runAfterBoth(cfB, Util::printTimeAndThread);
        cfAB.join();
    }
}
