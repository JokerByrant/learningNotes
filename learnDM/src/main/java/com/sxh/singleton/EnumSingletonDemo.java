package com.sxh.singleton;

/**
 * 枚举类单例模式
 * @author sxh
 * @date 2020/7/20
 */
public class EnumSingletonDemo {
    private enum Singleton{
        INSTANCE;

        private final EnumSingletonDemo instance;
        
        Singleton() {
            System.out.println("枚举类单例完成初始化！");
            instance = new EnumSingletonDemo();
        }

        public EnumSingletonDemo getInstance() {
            return instance;
        }
    }
    
    public static EnumSingletonDemo getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
