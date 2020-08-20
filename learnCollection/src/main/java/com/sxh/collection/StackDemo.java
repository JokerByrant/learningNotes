package com.sxh.collection;

import java.util.Stack;

/**
 * 栈的使用场景
 * @author sxh
 * @date 2020/7/21
 */
public class StackDemo {

    static Stack<Integer> s1 = new Stack<Integer>();
    static Stack<Integer> s2 = new Stack<Integer>();

    public void push(int node) {
        s1.push(node);
    }

    public int pop() throws Exception {
        if (s2.size() <= 0) {
            while (s1.size() > 0) {
                s2.push(s1.pop());
            }
        }
        if (s2.isEmpty()) {
            throw new Exception("stack is empty!");
        }
        return s2.pop();
    }

    public static void main(String[] args) {
        StackDemo stackDemo = new StackDemo();
        stackDemo.push(1);
        stackDemo.push(2);
        stackDemo.push(3);

        for (Integer integer : s1) {
            System.out.println(integer);
        }

        try {
            System.out.println(stackDemo.pop()+"");
            stackDemo.push(4);
            System.out.println(stackDemo.pop()+"");
            stackDemo.push(5);
            System.out.println(stackDemo.pop()+"");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("======================================");
        
        for (Integer integer : s1) {
            System.out.println(integer);
        }
    }

}
