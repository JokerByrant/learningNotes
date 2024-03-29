package com.sxh.ts.automatic;

import com.sxh.ts.entity.TransactionDemo;
import com.sxh.ts.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Spring框架的事务管理
 * @author sxh
 * @date 2021/11/15
 */
@Component
public class AutomaticTransaction {
    @Autowired
    private SqlExecutor sqlExecutor;
    @Autowired
    private AutomaticTransaction2 automaticTransaction2;

    @Transactional
    public void insert() {
        TransactionDemo transactionDemo = new TransactionDemo(UuidUtil.getUid(), new Date(), "sxh", new Date(), "sxh");
        int effectRow = sqlExecutor.doInsert(transactionDemo);
        if (effectRow == 0) {
            throw new RuntimeException("新增失败！");
        }
        automaticTransaction2.insert();
    }
}

