package com.sxh.aop.static_proxy;

/**
 * Aop是基于动态代理实现的，这个包中的Demo演示的是静态代理的实现
 * @author sxh
 * @date 2021/11/19
 */
public class Test {
    public static void main(String[] args) {
        InterfaceA proxy = new ProxyImplement();
        proxy.exec();
    }
}
