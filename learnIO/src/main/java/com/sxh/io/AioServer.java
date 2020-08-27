package com.sxh.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://developer.aliyun.com/article/726698
 * 模拟阻塞IO的读写操作
 * @author sxh
 * @date 2020/8/27
 */
public class AioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 7070), 50);

//        singleBioServer(serverSocket);
//        newThreadBioServer(serverSocket);
        threadPoolServer(serverSocket);
    }

    /**
     * 原始BIO
     * Socket Server会被客户端发出的IO指令阻塞
     * @throws IOException
     */
    public static void singleBioServer(ServerSocket serverSocket) throws IOException {
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("客户端:"+socket.getInetAddress().getLocalHost()+"已连接到服务器");
            InputStream is = socket.getInputStream();
            byte[] data = new byte[1024];
            is.read(data); // 当前线程会在这一步被阻塞，直到读取到数据

            System.out.println(new String(data, Charset.forName("UTF-8")));
            OutputStream out = socket.getOutputStream();
            out.write(data);
            socket.close();
        }
    }

    /**
     * 开启新线程处理客户端的IO操作
     * Server不会被客户端发出的IO指令所阻塞，但是会创建大量线程，浪费系统资源
     * @throws IOException
     */
    public static void newThreadBioServer(ServerSocket serverSocket) throws IOException {
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("客户端:"+socket.getInetAddress().getLocalHost()+"已连接到服务器");
            final Socket clientSocket = socket;
            new Thread(new Runnable() { // 创建一个新线程处理IO操作
                @Override
                public void run() {
                    try {
                        InputStream is = clientSocket.getInputStream();
                        byte[] data = new byte[1024];
                        is.read(data); // 当前线程会被阻塞，但是不会影响到Socket Server所在的线程

                        System.out.println(new String(data, Charset.forName("UTF-8")));
                        OutputStream out = clientSocket.getOutputStream();
                        out.write(data);
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 使用线程池管理客户端的连接
     * 这样可以限制客户端最大连接数，防止同一时间创建过多的线程
     * @throws IOException
     */
    public static void threadPoolServer(ServerSocket serverSocket) throws IOException {
        // 最多支持10个客户端进行连接
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Socket socket;

        while ((socket = serverSocket.accept()) != null) {
            System.out.println("客户端:"+socket.getInetAddress().getLocalHost()+"已连接到服务器");
            final Socket clientSocket = socket;
            executorService.submit(new Runnable() { // 在线程池创建一个新的线程
                @Override
                public void run() {
                    try {
                        InputStream is = clientSocket.getInputStream();
                        byte[]  data = new byte[1024];
                        is.read(data); // 当前线程会被阻塞，但是不会影响到Socket Server所在的线程

                        System.out.println(new String(data, Charset.forName("UTF-8")));
                        OutputStream out = clientSocket.getOutputStream();
                        out.write(data);
                        clientSocket.close();
                    } catch (Exception e) {
                        ;
                    }
                }
            });
        }
    }
}
