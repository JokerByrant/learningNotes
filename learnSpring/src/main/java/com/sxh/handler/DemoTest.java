package com.sxh.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sxh
 * @date 2021/3/19
 */
@Component
public class DemoTest {
    @Autowired
    private List<Handler> handlers;

    public void fun() {
        for (Handler handler : handlers) {
            handler.invoke();
        }
    }
}
