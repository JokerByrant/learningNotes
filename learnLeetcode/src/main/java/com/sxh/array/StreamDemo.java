package com.sxh.array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流Demo
 * @author sxh
 * @date 2020/12/2
 */
public class StreamDemo {
    // 1.利用Stream求和
    @Test
    public void fun1() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7));
        // 方式1
        int sum1 = set.stream().mapToInt(x -> x).sum();

        // 方式2
        Optional<Integer> reduce = set.stream().reduce((x, y) -> x + y);
        int sum2 = reduce.get();

        // 方式3
        int sum3 = set.stream().reduce(0, (x, y) -> x + y);

        // 方式4
        Integer sum4 = set.stream().collect(Collectors.summingInt(x -> x));

        // 数组求和
        int sumArr = Arrays.stream(set.toArray()).mapToInt(x -> (int) x).sum();

        System.out.println("方式1--->mapToInt求和结果：" + sum1);
        System.out.println("方式2--->reduce求和结果：" + sum2);
        System.out.println("方式3--->reduce求和结果：" + sum3);
        System.out.println("方式4--->collect求和结果：" + sum4);
        System.out.println("数组求和：" + sumArr);
    }

    // 2.基础类型数组转换成list、Set
    @Test
    public void fun2() {
        int[] a = {1,2,3,4,5,6};
        // 基础类型数组转换成List、Set有两种办法：第一种就是循环遍历，第二种是利用Stream的特性，List、Set的元素只能是包装类，因此需要调用boxed()对基础类型进行封装
        Set<Integer> set1 = Arrays.stream(a).boxed().collect(Collectors.toSet());
        System.out.println(set1);
        // 注：下面几种方法返回的都是Set<int[]>类型的集合
        Set<int[]> collect = Stream.of(a).collect(Collectors.toSet());
        Set<int[]> set = new HashSet<>(Arrays.asList(a));
    }
}
