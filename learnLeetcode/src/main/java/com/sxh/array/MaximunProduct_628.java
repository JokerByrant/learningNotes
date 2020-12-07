package com.sxh.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author 一池春水倾半城
 * @date 2020/12/7 23:21
 */
public class MaximunProduct_628 {
    /**
     * 1.数组全为负数，则最大乘积为最大的三个数相乘
     * 2.数组全为正数，则最大的乘积为最大的三个数相乘
     * 3.数组有正有负，则最大的乘积可能是最大的三个数相乘，也可能是最小的两个数与最大数的乘积
     * 综上，结果是 [最大的三个数的乘积] 或者 [最大的一个数乘以最小的两个数]
     * @param nums
     * @return
     */
    public int solution1(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 利用#414中的方法找出这5个数
        for(int num:nums) {
            // 找出最大的三个数
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            // 找出最小的两个数
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    /**
     * 利用排序
     * @param nums
     * @return
     */
    public int solution2(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        return Math.max(nums[length - 1] * nums[length - 2] * nums[length - 3], nums[0] * nums[1] * nums[length - 1]);
    }

    @Test
    public void test() {
        int s1 = solution1(new int[]{1, 2, 3, 4, 5});
        System.out.println(s1);
        int s2 = solution2(new int[]{1, 2, 3, 4, 5});
        System.out.println(s2);
    }
}
