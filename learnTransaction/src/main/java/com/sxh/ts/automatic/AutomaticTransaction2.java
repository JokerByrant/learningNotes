package com.sxh.ts.automatic;

import com.sxh.ts.entity.TransactionDemo;
import com.sxh.ts.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 这个方法用来测试事务的传播属性
 * @author sxh
 * @date 2021/11/19
 */
@Component
public class AutomaticTransaction2 {
    @Autowired
    private SqlExecutor sqlExecutor;

    @Transactional
    public void insert() {
        TransactionDemo transactionDemo = new TransactionDemo(UuidUtil.getUid(), new Date(), "sxh", new Date(), "sxh");
        int effectRow = sqlExecutor.doInsert(transactionDemo);
        if (effectRow == 0) {
            throw new RuntimeException("新增失败！");
        }
    }
}
