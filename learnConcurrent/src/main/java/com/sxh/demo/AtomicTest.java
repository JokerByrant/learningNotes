package com.sxh.demo;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sxh
 * @date 2021/2/23
 */
public class AtomicTest {
    private int i = 0;
    private AtomicInteger ai = new AtomicInteger(0);

    public void unUseAtomic(){
        i++;
    }

    public void useAtomic() {
        ai.addAndGet(1);
    }

    @Test
    public void fun() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                unUseAtomic();
                useAtomic();
            }).start();
        }
    }
}
