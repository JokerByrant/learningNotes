package com.sxh.array;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 数组的度 #697
 * @author 一池春水倾半城
 * @date 2020/12/10 23:07
 */
public class ShortestSubArray_697 {
    // 1.利用HashMap
    public int solution1(int[] nums) {
        // 首先找出数组的度
        // 接着找到这些度中元素出现的最左x和最右位置y
        int maxOccurence = 0, minLength = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>(); // 元素出现次数
        HashMap<Integer, Integer> larr = new HashMap<>(); // 元素出现的最左位置
        HashMap<Integer, Integer> rarr = new HashMap<>(); // 元素出现的最右位置
        for(int i = 0; i < nums.length; i++) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0)+1);
            if(larr.get(nums[i]) == null) {
                larr.put(nums[i], i);
            }
            rarr.put(nums[i], i);
        }
        for(int num:cnt.keySet()) {
            if(cnt.get(num) > maxOccurence) {
                // 更新度
                maxOccurence = cnt.get(num);
                // 更新最小长度
                minLength = rarr.get(num) - larr.get(num);
            } else if(cnt.get(num) == maxOccurence) { // 度长度一致，比较最短连续长度
                minLength = Math.min(minLength, rarr.get(num) - larr.get(num) + 1);
            }
        }

        return minLength;
    }

    @Test
    public void fun() {
        int[] nums = {1, 2, 2, 3, 1};
        solution1(nums);
    }
}
