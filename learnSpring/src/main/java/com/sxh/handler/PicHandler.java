package com.sxh.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2021/3/19
 */
@Order(2)
@Component
public class PicHandler extends Handler {
    @Override
    public void invoke() {
        print();
    }
}
