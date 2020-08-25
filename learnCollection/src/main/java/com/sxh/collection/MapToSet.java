package com.sxh.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * 测试Collections的newSetFromMap()方法
 * @author sxh
 * @date 2020/8/25
 */
public class MapToSet {
    @Test
    public void mapToSet() {
        HashMap<Integer, Boolean> map = new HashMap<>();
//        map.put(1,true);
        // 传入的map必须为空，而且参数类型必须是<E,Boolean>
        Set<Integer> set = Collections.newSetFromMap(map);
        set.add(1);
        System.out.println(set.toString());
        System.out.println(map.toString());
    }
}
