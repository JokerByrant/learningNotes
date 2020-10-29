package com.sxh.rpc.server;

import com.sxh.rpc.server.framework.RpcFrameWork;
import com.sxh.rpc.server.service.HelloService;
import com.sxh.rpc.server.service.HelloServiceImpl;

import java.io.IOException;

/**
 * @author sxh
 * @date 2020/10/29
 */
public class Main {
    public static void main(String[] args) throws IOException {
        HelloService service = new HelloServiceImpl();
        RpcFrameWork.export(service, 1234);
    }
}
