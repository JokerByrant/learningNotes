package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步任务异常处理
 * 注：下面几个方法的任务串行执行
 * @author sxh
 * @date 2021/11/16
 */
public class _06_Exception {
    /**
     * handle() -> 任务完成后执行BiFunction，结果消费，入参为结果或者异常，返回新结果
     * 无论上面任务正常还是异常，handle()都会执行
     */
    @Test
    public void _handle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Hello ";
        }).thenApply((value) -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b...");
            return value + " World！";
            // throw new RuntimeException("手动抛出异常!");
        }).handle((value, e) -> { // 处理任务b抛出的异常
            Util.sleep(1000);
            Util.printTimeAndThread("processing c...");
            if (e != null) {
                e.printStackTrace();
                return "Something is going wrong here！";
            } else {
                return value + "Everything is OK！";
            }
        });
        Util.printTimeAndThread(cf.get());
    }

    /**
     * whenComplete() -> 任务完成后执行BiConsumer，结果消费，入参为结果或者异常，不返回新结果
     * 无论上面任务正常还是异常，whenComplete()都会执行
     */
    @Test
    public void _whenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a");
            return "Method A";
            // throw new RuntimeException("手动抛出异常!");
        }).whenComplete((value, e) -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing b");
            if (e != null) {
                e.printStackTrace();
            } else {
                Util.printTimeAndThread(value + " --- Method B");
            }
        });
        // 只返回：Method A
        Util.printTimeAndThread(cf.get());
    }

    /**
     * exceptionally() -> 只有当任务出现异常时才执行exceptionally()，处理异常信息后返回新值。
     */
    @Test
    public void _exceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a");
            // return "Method A";
            throw new RuntimeException("手动抛出异常!");
        }).thenApply((value) -> { // 任务A出现异常，任务B将不执行
            Util.sleep(1000);
            Util.printTimeAndThread("processing b");
            return value + " --- Method b";
        }).exceptionally((e) -> { // 若上面两个任务没有出现异常，exceptionally将不执行
            Util.sleep(1000);
            Util.printTimeAndThread("processing c");
            if (e != null) {
                e.printStackTrace();
            }
            return "Something is going wrong here！";
        });
        Util.printTimeAndThread(cf.get());
    }
}
