package com.sxh.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单机测试netty最大连接数---客户端
 * @author sxh
 * @date 2021/11/24
 */
public class Client {
    private final Logger logger = LoggerFactory.getLogger(Client.class);

    //服务端的IP
    private static final String SERVER_HOST = "192.168.0.104";

    static final int BEGIN_PORT = 10000;
    static final int N_PORT = 100;

    public static void main(String[] args) {
        new Client().start(BEGIN_PORT, N_PORT);
    }

    public void start(final int beginPort, int nPort) {
        System.out.println("client starting....");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
            }
        });

        int index = 0;
        //从10000的端口开始，按端口递增的方式进行连接
        while (!Thread.interrupted()) {
            int port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        logger.error("连接失败！对应Netty服务端口：{}", port);
                        System.exit(0);
                    } else {
                        logger.info("连接成功！对应Netty服务端口：{}", port);
                    }
                });
                channelFuture.get();
            } catch (Exception e) {
            }

            if (++index == nPort) {
                index = 0;
            }
        }
    }
}
