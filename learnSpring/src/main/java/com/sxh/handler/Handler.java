package com.sxh.handler;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sxh
 * @date 2021/3/19
 */
public abstract class Handler {
    @Autowired
    private TestManager testManager;

    public abstract void invoke();

    public void print() {
        testManager.print();
    }
}
