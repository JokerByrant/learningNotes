package com.sxh.aqs;

/**
 * @author sxh
 * @date 2021/6/11
 */
public class SimpleAqsLockMain {
    static int count = 0;
    static SimpleAqsLock lock = new SimpleAqsLock();

    public static void main (String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                lock.lock();
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
