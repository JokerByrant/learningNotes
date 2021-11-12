package com.sxh.ts.manual;

import com.sxh.ts.sql.ConnectionManager;
import com.sxh.ts.sql.SqlExecutor;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 手动管理数据库连接
 *
 * @author sxh
 * @date 2021/11/12
 */
public class Demo1 {
    @Test
    public void fun1() throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection1();
            SqlExecutor.doInsert(connection);
            SqlExecutor.doInsert(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
