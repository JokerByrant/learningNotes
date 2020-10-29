package com.sxh.object;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象
 * @author sxh
 * @date 2020/9/11
 */
public class ImmutableObject {
    // 不可变Map
    @Test
    public void unmodifiableMap() {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a", 1);
    }
}
