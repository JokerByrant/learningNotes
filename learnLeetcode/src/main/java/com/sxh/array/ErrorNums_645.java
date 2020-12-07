package com.sxh.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 错误集合 #645
 * @author sxh
 * @date 2020/12/3
 */
public class ErrorNums_645 {
    // 1.利用HashMap
    public int[] findErrorNums1(int[] nums) {
        int lost = -1;
        int repeat = -1;
        Map<Integer, Integer> map = new HashMap<>();
        // HashMap中存放{num, count}，num->数字，count->出现次数
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(int i = 1; i <= nums.length; i++) {
            if(map.containsKey(i)) {
                // 如果出现次数超过1次，则表示该数是重复数字
                if(map.get(i) > 1) {
                    repeat = i;
                }
                // 如果Map中没有这个数字，表示该数是缺失数
            } else {
                lost = i;
            }
        }
        return new int[]{repeat, lost};
    }

    // 2.利用HashSet和等差数列求和公式
    public int[] findErrorNums2(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int setSum = set.stream().mapToInt(x->x).sum();
        int arrSum = Arrays.stream(nums).sum();
        // 正确的总和，利用等差数列求和公式
        int realSum = nums.length*1 + nums.length*(nums.length-1) * 1/2;
        // 重复的数
        int repeat = arrSum - setSum;
        // 丢失的数
        int lost = realSum - setSum;
        return new int[]{repeat, lost};
    }

    @Test
    public void fun1() {
        int[] arr = {1,2,3,3,4,5};
        int[] errorNums1 = findErrorNums1(arr);
        int[] errorNums2 = findErrorNums2(arr);
        System.out.println(Arrays.stream(errorNums1).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(errorNums2).boxed().collect(Collectors.toList()));
    }
}
