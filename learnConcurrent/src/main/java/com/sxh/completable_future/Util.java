package com.sxh.completable_future;

/**
 * @author sxh
 * @date 2021/11/15
 */
public class Util {
    protected static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
