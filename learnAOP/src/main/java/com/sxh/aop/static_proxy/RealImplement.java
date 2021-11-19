package com.sxh.aop.static_proxy;

/**
 * @author sxh
 * @date 2021/11/19
 */
public class RealImplement implements InterfaceA{
    @Override
    public void exec() {
        System.out.println("real impl");
    }
}
