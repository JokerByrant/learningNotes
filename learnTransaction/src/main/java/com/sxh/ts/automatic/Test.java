package com.sxh.ts.automatic;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类---测试使用Spring的事务来处理sql
 * @author sxh
 * @date 2021/11/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private AutomaticTransaction automaticTransaction;
    @Autowired
    private AutomaticTransaction2 automaticTransaction2;

    @org.junit.Test
    public void _test_Transactional() {
        automaticTransaction.insert();
        automaticTransaction2.insert();
    }
}
