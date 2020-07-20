package com.sxh.singleton;

/**
 * 静态内部类单例模式
 * @author sxh
 * @date 2020/7/20
 */
public class StaticClassSingletonDemo {
    private static class Singleton{
        static {
            System.out.println("静态内部类单例完成初始化！");
        }
        private final static StaticClassSingletonDemo instance = new StaticClassSingletonDemo();
    }

    public static StaticClassSingletonDemo getInstance() {
        return Singleton.instance;
    }
}
