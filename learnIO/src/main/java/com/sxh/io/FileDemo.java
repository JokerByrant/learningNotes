package com.sxh.io;

import org.junit.Test;

import java.io.*;
import java.util.*;

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

    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    byte[] arrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    /**
     * ByteArrayOutputStream使用
     * @throws IOException
     */
    @Test
    public void fun11() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 依次写入A、B、C三个字母
        baos.write(0x41);
        baos.write(0x42);
        baos.write(0x43);
        System.out.println("baos = " + baos);

        // 将arrayLetters从下标为3开始的后5个位置的元素写入到baos中，即0x64, 0x65, 0x66, 0x67, 0x68
        baos.write(arrayLetters, 3, 5);
        System.out.println("baos = " + baos);

        int size = baos.size();
        System.out.println("size = " + size);

        // 转换成byte数组
        byte[] bytes = baos.toByteArray();
        String str = new String(bytes);
        System.out.println("str = " + str);

        // 转换成String字符串
        String baosStr = baos.toString();
        System.out.println("baosStr = " + baosStr);

        // 将baos写入到另一个输出流中
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        // writeTo(OutputStream out) 将该“字节数组输出流”的数据全部写入到“输出流out”中。
        baos.writeTo(baos2);
        System.out.println("baos2 = " +baos2);
    }


    @Test
    public void fun12() {
        int len = 5;

        ByteArrayInputStream bais = new ByteArrayInputStream(arrayLetters);

        // 从字节流中读取5个字节
        for (int i = 0; i < len; i++) {
            // 若能继续读取下一个字节，则读取下一个字节
            if (bais.available() >= 0) {
                // 读取字节流的下一个字节
                int read = bais.read();
                System.out.println(read);
            }
        }

        // 若该“字节流”不支持标记功能，则直接退出
        if (!bais.markSupported()) {
            System.out.println("not support mark!");
            return;
        }

        // 标记“字节流中下一个被读取的位置”，这里的0没有实际意义，可以指定为任何数，它只作为一个记录数存在
        // mark()方法通常与reset()方法连用，mark()标记当前读取指针的位置，reset()将读取指针回溯到标记的位置
        bais.mark(0);

        // 跳过5个字节。跳过5个字节后，字节流中下一个被读取的值应该是“0x6B”。
        bais.skip(5);

        // 从字节流中读取5个数据
        byte[] buf = new byte[len];
        bais.read(buf, 0, len);
        String str1 = new String(buf);
        System.out.println(str1);

        // 重置字节流到mark()标记的地方
        bais.reset();

        // 从重置后的字节流读取5个字节到buf中
        bais.read(buf, 0, len);
        String str2 = new String(buf);
        System.out.println("重置后的字节流：" + str2);
    }

    @Test
    public void fun13() {
        List<String> stringList = new ArrayList<String>();
        stringList.add(null);
        stringList.add("aa");
        for (String item:stringList) {
            System.out.println(item);
        }
    }

    @Test
    public void fun14() {
        Student student = new Student("张三", "123", 11);
        Student student1 = new Student("张三", "123", 12);
        List<Student> list = new ArrayList<Student>();
        list.add(student);
        list.add(student1);
        Set<Student> set = new TreeSet<Student>(Comparator.comparing(Student::getName));
        set.addAll(list);

        System.out.println(set);
    }
}
