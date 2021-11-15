package com.sxh.completable_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务构建方法
 * @author sxh
 * @link 参考文档：https://segmentfault.com/a/1190000039721242
 * @date 2021/11/15
 */
public class _02_Build {
    /**
     * runAsync() -> 进行数据处理，接收前一步骤传递的数据，无返回值
     */
    @Test
    public void _runAsync() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            Util.sleep(1000);
            System.out.println("Hello World!");
        });
        cf.join();
    }

    /**
     * supplyAsync() -> 进行数据处理，接收前一步骤传递的数据，有返回值。
     */
    @Test
    public void _supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            Util.sleep(1000);
            return "Hello World!";
        });
        cf.join();
        System.out.println(cf.get());
    }
}
