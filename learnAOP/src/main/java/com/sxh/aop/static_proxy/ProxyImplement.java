package com.sxh.aop.static_proxy;

/**
 * @author sxh
 * @date 2021/11/19
 */
public class ProxyImplement implements InterfaceA{
    private InterfaceA interfaceA;

    public ProxyImplement () {
        interfaceA = new RealImplement();
    }

    @Override
    public void exec() {
        System.out.println("doSomeThing Before...");
        interfaceA.exec();
        System.out.println("doSomeThing After...");
    }
}
