package com.sxh.rpc.server.service;

/**
 * @author sxh
 * @date 2020/10/29
 */
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "hello, this is " + name;
    }
}
