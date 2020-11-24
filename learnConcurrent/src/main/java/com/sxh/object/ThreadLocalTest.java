package com.sxh.object;

import java.util.Random;

/**
 * @author 一池春水倾半城
 * @date 2020/11/24 20:49
 */
public class ThreadLocalTest implements Runnable{
    ThreadLocal<Student> StudentThreadLocal = new ThreadLocal<Student>();
    ThreadLocal<Student> StudentThreadLocal2 = new ThreadLocal<Student>();

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        Random random = new Random();
        Student student = getStudentt(); //通过这个方法，为每个线程都独立的new一个student对象，每个线程的的student对象都可以设置不同的值
        Student student2 = getStudentt2();
        student.setAge(random.nextInt(100));
        student2.setAge(random.nextInt(100));
        // 同一个线程中通过getStudent()方法拿到的Student对象都是同一个，因为是从同一个ThreadLocal中获取的
        System.out.println(currentThreadName + " is first get Student1 age: " + getStudentt().getAge());
        System.err.println(currentThreadName + " is first get Student2 age: " + getStudentt2().getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( currentThreadName + " is second get Student1 age: " + getStudentt().getAge());
        System.err.println( currentThreadName + " is second get Student2 age: " + getStudentt2().getAge());

    }

    private Student getStudentt() {
        // 从当前线程的ThreadLocalMap中，以StudentThreadLocal为key拿到student对象，如果未初始化则返回空
        Student Student = StudentThreadLocal.get();
        if (null == Student) {
            Student = new Student();
            // 进到源码里面发现，set操作就是当前<ThreadLocal，T>，这里是<StudentThreadLocal,Student>以(key,value)的形式存储到ThreadLocalMap中
            // 通过getMap()方法可以确定这个ThreadLocalMap是当前线程的引用
            // 也就是说，每个线程中都维护了一个ThreadLocalMap来保存线程中创建的<ThreadLocal，T>，一个ThreadLocal对应一个对象
            StudentThreadLocal.set(Student);
        }
        return Student;
    }

    private Student getStudentt2() {
        Student Student = StudentThreadLocal2.get();
        if (null == Student) {
            Student = new Student();
            StudentThreadLocal2.set(Student);
        }
        return Student;
    }

    public static void main(String[] args) {
        ThreadLocalTest t = new ThreadLocalTest();
        Thread t1 = new Thread(t,"Thread A");
        Thread t2 = new Thread(t,"Thread B");
        t1.start();
        t2.start();
    }

}

class Student{
    int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
