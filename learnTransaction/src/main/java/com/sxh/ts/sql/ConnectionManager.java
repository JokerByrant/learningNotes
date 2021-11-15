package com.sxh.ts.sql;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 获取数据库连接
 *
 * @author sxh
 * @date 2021/11/12
 */
public class ConnectionManager {
    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * 方式1: 通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
     */
    public static Connection getConnection1() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 方式2：使用驱动管理器来连接数据库
     * @return
     * @throws Exception
     */
    public static Connection getConnection2() throws Exception {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 方式3：读取配置文件方式
     * @return
     * @throws Exception
     */
    public static Connection getConnection3() throws Exception {
        Driver driver = new Driver();
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return driver.connect(url, props);
    }
}
