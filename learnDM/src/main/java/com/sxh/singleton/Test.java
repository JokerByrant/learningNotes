package com.sxh.singleton;

/**
 * @author sxh
 * @date 2020/7/20
 */
public class Test {
    public static void main(String[] args) {
        enumSingletonTest();

        staticClassSingletonTest();

        doubleCheckLockSingletonTest();
    }
    
    // 测试枚举单例
    private static void enumSingletonTest() {
        EnumSingletonDemo e1 = EnumSingletonDemo.getInstance();
        EnumSingletonDemo e2 = EnumSingletonDemo.getInstance();
        System.out.println(e1 == e2);
    }

    // 测试静态内部类单例
    private static void staticClassSingletonTest() {
        StaticClassSingletonDemo s1 = StaticClassSingletonDemo.getInstance();
        StaticClassSingletonDemo s2 = StaticClassSingletonDemo.getInstance();
        System.out.println(s1 == s2);
    }

    // 测试双向检查锁单例
    private static void doubleCheckLockSingletonTest() {
        DoubleCheckLockSingletonDemo d1 = DoubleCheckLockSingletonDemo.getInstance();
        DoubleCheckLockSingletonDemo d2 = DoubleCheckLockSingletonDemo.getInstance();
        System.out.println(d1 == d2);
    }
}
