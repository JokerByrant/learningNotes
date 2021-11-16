package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 合并多个任务为一个任务
 * 注：下面几个方法中的任务都是并行执行的
 * @author sxh
 * @date 2021/11/16
 */
public class _07_Combine {
    /**
     * allOf() -> 合并多个complete为一个，等待全部完成
     */
    @Test
    public void _allOf() {
        // 创建异步执行任务:
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Method a";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread("processing b...");
            return "Method b";
        });
        CompletableFuture<String> cfC = CompletableFuture.supplyAsync(() -> {
            Util.sleep(3000);
            Util.printTimeAndThread("processing c...");
            return "Method c";
        });
        // allOf等待所有任务执行完成才执行cfD，如果有一个任务异常终止，则cfD.get时会抛出异常，都是正常执行，cfD.get返回null
        CompletableFuture<Void> cfD = CompletableFuture.allOf(cfA, cfB, cfC).whenComplete((value, e) -> {
            Util.printTimeAndThread("processing d...");
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.println("任务执行成功！" + value);
            }
        });
        cfD.join();
    }

    /**
     * anyOf() -> 合并多个complete为一个，等待其中之一完成
     */
    @Test
    public void _anyOf() {
        // 创建异步执行任务:
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            Util.printTimeAndThread("processing a...");
            return "Method a";
        });
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            Util.sleep(2000);
            Util.printTimeAndThread("processing b...");
            return "Method b";
        });
        CompletableFuture<String> cfC = CompletableFuture.supplyAsync(() -> {
            Util.sleep(3000);
            Util.printTimeAndThread("processing c...");
            return "Method c";
        });
        // anyOf是只有一个任务执行完成，无论是正常执行或者执行异常，都会执行cfD，cfD.get的结果就是已执行完成的任务的执行结果
        CompletableFuture<Object> cfD = CompletableFuture.anyOf(cfA, cfB, cfC).whenComplete((value, e) -> {
            Util.printTimeAndThread("processing d...");
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.println("任务执行成功！" + value);
            }
        });
        // 返回：任务执行成功！Method a
        cfD.join();
        // 等待所有任务执行完毕
        Util.sleep(3000);
    }
}
