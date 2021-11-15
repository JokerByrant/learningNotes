package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务消费方法
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
            return "first";
        }).thenAccept((result) -> System.out.println("任务执行完毕：" + result));
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
            return "first";
        }).thenApply((result) -> {
            Util.sleep(1000);
            return result + "--" + "second";
        });
        // 主线程阻塞，直到cf任务完成
        System.out.println(cf.get());
    }

    /**
     * thenRun() -> 无返回值，并且无入参。上一阶段完成后，执行本阶段的任务。
     */
    @Test
    public void _thenRun() {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            return "first";
        }).thenRun(() -> {
            Util.sleep(1000);
            System.out.println("第二阶段任务完成！");
        });
        cf.join();
    }
}
