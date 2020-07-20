package com.sxh.learnjvm;

/**
 * @author 一池春水倾半城
 * @date 2020/7/13 21:58
 */
public class Test1 {
    static {
        System.out.println("静态代码块执行了！");
    }

    public static void main(String[] args) {
        DemoEnumSingleton d1 = DemoEnumSingleton.getInstance();
        DemoEnumSingleton d2 = DemoEnumSingleton.getInstance();
        System.out.println(d1 == d2);
    }
}
