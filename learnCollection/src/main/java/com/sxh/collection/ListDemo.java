package com.sxh.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sxh
 * @date 2020/12/15
 */
public class ListDemo {
    @Test
    public void fun() {
        String[] arr = {"1", "2", "3", "4"};
        Set<String> set = new HashSet<>(arr.length);
        System.out.println(set);
    }

    @Test
    public void fun1() {
        Object object = new Student();
        if (object instanceof Student) {
            System.out.println(111);
        }
    }

    class Student {
    }
}
