package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务消费方法
 * 注：下面几个方法中，任务串行执行，几个任务的执行在同一线程中进行。
 * @author sxh
 * @link 参考文档：https://segmentfault.com/a/1190000039721242
 * @date 2021/11/15
 */
public class _03_Consume {
    /**
     * thenAccept() -> 消费前一阶段的结果
     */
    @Test
    public void _thenAccept() {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "first";
        }).thenAccept((result) -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            Util.printTimeAndThread("有入参: " + result + "，无返回值！");
        });
        cf.join();
    }

    /**
     * thenApply() -> 在前一个阶段上应用thenApply函数，将上一阶段完成的结果作为当前阶段的入参
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void _thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "first";
        }).thenApply((result) -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            return result + "--" + "second";
        });
        // 主线程阻塞，直到cf任务完成
        Util.printTimeAndThread(cf.get());
    }

    /**
     * thenRun() -> 无返回值，并且无入参。上一阶段完成后，执行本阶段的任务。
     */
    @Test
    public void _thenRun() {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "first";
        }).thenRun(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            Util.printTimeAndThread("无入参，无返回值！");
        });
        cf.join();
    }

    /**
     * thenCompose() -> 当原任务完成后，以其结果为参数，返回一个新的任务(类似flatMap，与上面几个方法区分开来)。
     */
    @Test
    public void _thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.printTimeAndThread("processing a...");
            Util.sleep(1000);
            return "Milk";
        }).thenComposeAsync((value) -> CompletableFuture.supplyAsync(() -> {
            Util.printTimeAndThread("processing b...");
            Util.sleep(1000);
            return value + " Love Bread.";
        })).thenCompose((value) -> CompletableFuture.supplyAsync(() -> {
            Util.printTimeAndThread("processing c...");
            Util.sleep(1000);
            return value + " I Love You!";
        })).thenApplyAsync((value) -> {
            Util.printTimeAndThread("processing d...");
            Util.sleep(1000);
            return "结果：" + value;
        });
        Util.printTimeAndThread(cf.get());
    }
}
