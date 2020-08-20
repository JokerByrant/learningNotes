package com.sxh.collection;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用linkedHashMap并开启Lru策略
 * @author sxh
 * @date 2020/8/20
 */
public class LruDemo {
    class LRU<K, V> extends LinkedHashMap<K, V> {
        private int capacity; // 最大缓存容量

        public LRU(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        // 重写removeEldestEntry()以便移除旧元素
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    // 测试Lru
    @Test
    public void LruTest() {
        LRU<Integer, Integer> lru = new LRU<Integer, Integer>(5);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        lru.put(7, 7);

        System.out.println(lru.get(4));

        lru.put(6, 666);

        // 输出: {3=3, 5=5, 7=7, 4=4, 6=666}，可以看到最旧的元素被删除了，且最近访问的4被移到了后面
        System.out.println(lru);
    }

    @Test
    public void NormalTest() {
        LinkedHashMap<Integer, Integer> lru = new LinkedHashMap<Integer, Integer>(5);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        lru.put(7, 7);

        System.out.println(lru.get(4));

        lru.put(6, 666);

        // 输出: {1=1, 2=2, 3=3, 4=4, 5=5, 6=666, 7=7}
        System.out.println(lru);
    }

}
