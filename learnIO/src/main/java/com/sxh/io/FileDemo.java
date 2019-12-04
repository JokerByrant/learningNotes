package com.sxh.io;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.*;

/**
 * @author 一池春水倾半城
 * @date 2019/12/4
 */
public class FileDemo {
    String file = "D://1.txt";

    /**
     * FileInputStream使用
     * @throws IOException
     */
    @Test
    public void fun1() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        // 创建一个长度为1024的竹筒，用于存放从文件读取的字节
        byte[] bytes = new byte[1024];
        // 记录实际的字节数
        int hasRead;
        while ((hasRead=fis.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, hasRead));
        }
    }

    /**
     * FileReader使用
     * @throws IOException
     */
    @Test
    public void fun2() throws IOException {
        FileReader fr = new FileReader(file);
        // 创建一个长度为1024的竹筒，用于存放从文件读取的字符
        char[] chars = new char[1024];
        // 记录实际的字符数
        int hasRead;
        while ((hasRead=fr.read(chars)) != -1) {
            System.out.println(new String(chars, 0, hasRead));
        }
    }

    /**
     * FileOutputStream使用
     * @throws IOException
     */
    @Test
    public void fun3() throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        String outputStr = "测试FileOutputStream输出！";
        // 转化成字节
        byte[] bytes = outputStr.getBytes();
        fos.write(bytes);
        fos.close();

        fun1();
    }

    /**
     * FileWriter使用
     * @throws IOException
     */
    @Test
    public void fun4() throws IOException {
        FileWriter fw = new FileWriter(file);
        String outputStr = "测试FileWriter输出！";
        fw.write(outputStr);
        fw.close();

        fun2();
    }

    /**
     * 缓冲流使用
     * @throws IOException
     */
    @Test
    public void fun5() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file);
            // 缓冲流是处理流，不能直接处理数据，只能处理封装过的节点流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            String output = "缓冲输出流BufferedOutputStream测试";
            byte[] bytes = output.getBytes();
            bos.write(bytes);

            bytes = new byte[1024];
            int hasRead = 0;
            if ((hasRead = bis.read(bytes)) != -1) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
