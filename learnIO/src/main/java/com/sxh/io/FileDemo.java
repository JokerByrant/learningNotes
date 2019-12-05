package com.sxh.io;

import org.junit.Test;

import java.io.*;

/**
 * @author 一池春水倾半城
 * @date 2019/12/4
 */
public class FileDemo {
    final static String file = "D://1.txt";

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
     * 缓冲流BufferedInputStream、BufferedOutputStream使用
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

            String output = "缓冲输出流BufferedOutputStream测试111";
            byte[] bytes = output.getBytes();
            bos.write(bytes);
            // 写完数据记得关闭缓冲输出流，否则数据无法正常写到文件中
            bos.close();

            bytes = new byte[1024];
            int hasRead = 0;
            if ((hasRead = bis.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, hasRead));
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲流BufferedReader、BufferedWriter使用
     * @throws IOException
     */
    @Test
    public void fun6() throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            fr = new FileReader(file);
            fw = new FileWriter(file);
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);

            String out = "缓冲输出流BufferedWriter测试";
            bw.write(out);
            bw.close();

            char[] chars = new char[1024];
            int hasRead = 0;
            if ((hasRead = br.read(chars)) != -1) {
                System.out.println(new String(chars, 0 , hasRead));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换流OutputStreamWriter、InputStreamReader的使用
     * @throws IOException
     */
    @Test
    public void fun7() throws IOException {
        /*
            通过转换流写数据
        */
        FileOutputStream fo = new FileOutputStream(file);
        // 转换流OutputStreamWriter继承于Writer类，将字节流转换为字符流
        OutputStreamWriter osw = new OutputStreamWriter(fo);
        String out = "测试转换流OutputStreamWriter写数据到文件！";
        osw.write(out);
        osw.close();

        /*
            通过转换流读数据
         */
        FileInputStream fis = new FileInputStream(file);
        // 转换流InputStreamReader继承于Reader类，将字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(fis);
        char[] chars = new char[1024];
        int hasRead = 0;
        if ((hasRead = isr.read(chars)) != -1) {
            System.out.println(new String(chars, 0, hasRead));
        }
        isr.close();
    }

    /**
     * 通过转换流InputStreamReader获取控制台输入
     * @throws IOException
     */
    public static void fun8() throws IOException {
        // 将InputStream类型的字节流System.in转换为Reader类型的字符流
        InputStreamReader isr = new InputStreamReader(System.in);
        // 将Reader包装为BufferedReader，使其具有缓存功能，可以一行一行的读取文本
        BufferedReader br = new BufferedReader(isr);
        String buffer = null;
        // 获取控制台输入
        while ((buffer = br.readLine()) != null) {
            if (buffer.equals("bye")) {
                System.out.println("小爱同学：再见！");
                break;
            }
            System.out.println("小爱同学：" + buffer);
        }
    }

    public static void main(String[] args) throws IOException {
        fun8();
    }

    /**
     * 对象流ObjectInputStream/ObjectOutputStream使用
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void fun9() throws IOException, ClassNotFoundException {
        writeObject();
        readObject();
    }

    /**
     * 写对象
     * @throws IOException
     */
    public static void writeObject() throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        // BufferedOutputStream和ObjectOutputStream都是处理流
        BufferedOutputStream bos = new BufferedOutputStream(os);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        Student student = new Student("朱晓宇", "abc123", 19);
        oos.writeObject(student);
        oos.close();
    }

    /**
     * 读取对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readObject() throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(file);
        // 使用缓冲流（BufferedInputStream）封装一下字节流（FileInputStream）
        BufferedInputStream bis = new BufferedInputStream(fs);
        // 再加一层处理流（ObjectInputStream）
        ObjectInputStream ois = new ObjectInputStream(bis);

        Student student = (Student) ois.readObject();
        System.out.println("读取到对象: " + student);

        /**
         * 输出：Student(name=朱晓宇, pwd=null, age=19)
         * 为了密码安全，在pwd上加了transient关键词，因此pwd没有被序列化
         */
        ois.close();
    }

    /**
     * System.out.println()是什么
     */
    public void fun10() {
        // println是PrintStream的一个方法
        // out是一个静态PrintStream类型的成员变量
        // System是java.lang的一个类，用于和底层操作系统交互
        System.out.println();
    }
}
