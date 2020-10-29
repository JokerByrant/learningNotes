package com.sxh.rpc.client;

import com.sxh.rpc.server.framework.RpcFrameWork;
import com.sxh.rpc.server.service.HelloService;

/**
 * @author sxh
 * @date 2020/10/29
 */
public class Main {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFrameWork.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
