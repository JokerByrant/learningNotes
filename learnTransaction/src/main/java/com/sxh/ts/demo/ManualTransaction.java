package com.sxh.ts.demo;

import com.sxh.ts.sql.ConnectionManager;
import com.sxh.ts.sql.SqlExecutor;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 手动管理数据库连接
 * @author sxh
 * @date 2021/11/15
 */
public class ManualTransaction {
    /**
     * 执行 commit() 方法后，数据才会插入数据库
     * @throws SQLException
     */
    @Test
    public void fun1() throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection1();
            connection.setAutoCommit(false);
            SqlExecutor.doInsert(connection);
            SqlExecutor.doInsert(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
