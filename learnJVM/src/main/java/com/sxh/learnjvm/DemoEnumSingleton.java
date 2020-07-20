package com.sxh.learnjvm;

/**
 * 通过枚举完成单例模式的设计
 * @author sxh
 * @date 2020/7/20
 */
public class DemoEnumSingleton {
    private enum Singleton{
        INSTANCE;

        private final DemoEnumSingleton instance;
        
        Singleton() {
            System.out.println("Demo2对象初始化！");
            instance = new DemoEnumSingleton();
        }

        public DemoEnumSingleton getInstance() {
            return instance;
        }
    }
    
    public static DemoEnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
