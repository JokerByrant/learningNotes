package com.sxh.singleton;

/**
 * 双向检查锁单例
 * @author sxh
 * @date 2020/7/20
 */
public class DoubleCheckLockSingletonDemo {
    // volatile防止指令重排序，因为JVM有一个机制：可以不按照程序的执行顺序执行，尽可能的提高执行效率
    private volatile static DoubleCheckLockSingletonDemo instance;
    
    private DoubleCheckLockSingletonDemo() {}

    public static DoubleCheckLockSingletonDemo getInstance() {
        // 第一次判断，如果这里为空，不进入抢锁阶段，直接返回实例
        if (instance == null) {
            synchronized (DoubleCheckLockSingletonDemo.class) {
                // 抢到锁之后再次判断是否为空
                if (instance == null) {
                    System.out.println("双向检查锁单例初始化完成！");
                    instance = new DoubleCheckLockSingletonDemo();
                }
            }
        }
        
        return instance;
    }
}
