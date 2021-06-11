package com.sxh.cc;

/**
 * @author sxh
 * @date 2021/3/16
 */
public class Demo {
    public static void fun(Integer tmp) {
        tmp = tmp * 3;
        System.out.println(tmp);
    }

    public static void main(String[] args) {
        Integer tmp = 5;
        fun(tmp);
        System.out.println(tmp);
    }
}
