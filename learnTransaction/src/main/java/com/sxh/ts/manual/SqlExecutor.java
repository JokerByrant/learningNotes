package com.sxh.ts.manual;

import com.sxh.ts.util.DateUtil;
import com.sxh.ts.util.UuidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 执行Sql
 *
 * @author sxh
 * @date 2021/11/12
 */
public class SqlExecutor {
    public static void doInsert(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into t_transaction_demo (t_uid, update_date, update_user, create_date, create_user) values (?, ?, ?, ?, ?)");
        ps.setString(1, UuidUtil.getUid());
        ps.setString(2, DateUtil.getTodayStr());
        ps.setString(3, "sxh");
        ps.setString(4, DateUtil.getTodayStr());
        ps.setString(5, "sxh");
        ps.executeUpdate();
    }
}
