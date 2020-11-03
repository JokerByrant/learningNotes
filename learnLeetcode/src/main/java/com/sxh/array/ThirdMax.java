package com.sxh.array;

import java.util.TreeSet;

/**
 * 第三大的数 #414
 * 描述：给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * @author sxh
 * @date 2020/11/25
 */
public class ThirdMax {
    // 1.利用TreeSet是有序的这一特性
    int solution1(int[] nums) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int num:nums) {
            // 添加到TreeSet中的数会自动完成排序
            set.add(num);
            // 如果TreeSet大小大于3，则移除最小的元素，first()--->获取头元素
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() == 3 ? set.first() : set.last();
    }

    // 2.维护三个元素来记录数组中的头三个最大数
    int solution2(int[] nums) {
        // 维护三个变量，分别记录最大，第二大，第三大的数
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for(int num:nums) {
            if(num == first || num == second || num == third) continue;
            if (num > first) { // 找到最大数，将历史最大数向下移位
                third = second;
                second = first;
                first = num;
            } else if (num > second) {
                third = second;
                second = num;
            } else if (num > third) {
                third = num;
            }
        }
        return (int)(third == Long.MIN_VALUE ? first : third);
    }
}
