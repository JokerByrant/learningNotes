package com.sxh.io;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author sxh
 * @date 2020/8/27
 */
public class AioClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",7070);

        //构建IO
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();;
        //向服务器端发送一条消息
        bw.write(inputStr);
        bw.flush();

        //读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String mess = br.readLine();
        System.out.println("服务器："+mess);
    }
}
