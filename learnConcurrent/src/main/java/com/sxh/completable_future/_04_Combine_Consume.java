package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 异步任务结果合并消费
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
            System.out.println("processing a...");
            return "hello";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            System.out.println("processing b...");
            return " world";
        });
        CompletableFuture<String> cfC = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            System.out.println("processing c...");
            return ", I'm CodingTao!";
        });
        // 上面三个任务是异步执行的，相互之间不阻塞
        CompletableFuture<String> cfABC = cfA.thenCombine(cfB, (resultA, resultB) -> {
            System.out.println(resultA + resultB);  // hello world
            return resultA + resultB;
        }).thenCombine(cfC, (resultAB, resultC) -> {
            System.out.println(resultAB + resultC); // hello world, I'm CodingTao!
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
            System.out.println("processing a...");
            return "hello";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            System.out.println("processing b...");
            return " world";
        });
        CompletableFuture<Void> cfAB = cfA.thenAcceptBoth(cfB, (resultA, resultB) -> {
            System.out.println(resultA + resultB);
        });
        cfAB.join();
    }
}
