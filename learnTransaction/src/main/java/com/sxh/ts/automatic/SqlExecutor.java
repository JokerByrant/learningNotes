package com.sxh.ts.automatic;

import com.sxh.ts.entity.TransactionDemo;
import org.apache.ibatis.annotations.Insert;

/**
 * Mybatis操作sql
 * @author sxh
 * @date 2021/11/18
 */
public interface SqlExecutor {
    @Insert("insert into t_transaction_demo (t_uid, update_date, update_user, create_date, create_user) values (#{tUid}, #{updateDate}, #{updateUser}, #{createDate}, #{createUser})")
    int doInsert(TransactionDemo transactionDemo);
}
