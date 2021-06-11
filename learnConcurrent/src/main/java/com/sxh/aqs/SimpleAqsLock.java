package com.sxh.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义锁，面向用户
 *
 * @author sxh
 * @date 2021/6/11
 */
public class SimpleAqsLock {
    /**
     * 自定义同步器，面向AQS，衔接起Aqs底层方法和面向用户层方法
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public void unlock () {
        sync.release(1);
    }
}
