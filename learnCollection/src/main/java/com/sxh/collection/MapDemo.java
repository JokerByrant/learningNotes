package com.sxh.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author sxh
 * @date 2020/7/24
 */
public class MapDemo {
    public static void main(String[] args) {
        treeMapTest();
    }
    
    private static void treeMapTest() {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(1, "张三");
        map.put(12, "李四");
        map.put(3, "王五");
        map.put(null, "赵六");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println(next.getKey() + " : " + next.getValue());
        }
    }
}
