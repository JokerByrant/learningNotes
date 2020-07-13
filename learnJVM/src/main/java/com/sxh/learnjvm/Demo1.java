package com.sxh.learnjvm;

import org.junit.Test;

/**
 * @author 一池春水倾半城
 * @date 2020/7/13 21:57
 */
public class Demo1 {
    // 1.通过classloader加载类，不会执行静态块
    @Test
    public void loadClass1() throws ClassNotFoundException {
        ClassLoader loader = Demo1.class.getClassLoader();
        loader.loadClass(Test1.class.getName());
    }

    // 2 使用Class.forName()加载类，会执行静态块
    @Test
    public void loadClass2() throws ClassNotFoundException {
        Class.forName(Test1.class.getName());
    }

    // 3. 使用Class.forName()和classloader结合的方式加载类，执行静态块取决于initialize参数
    @Test
    public void loadClass3() throws ClassNotFoundException {
        ClassLoader loader = Demo1.class.getClassLoader();
        // initialize: false->不加载static块， true->加载static块
        Class.forName(Test1.class.getName(), false, loader);
    }
}
