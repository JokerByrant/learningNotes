package com.sxh.ts.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 自定义事务管理器(这里给出的Demo并不完善，只指定了自定义的事务管理器，但是没有配置数据源)
 * 这里定义的事务管理是当开启了@Transactional注解时，寻找的事务管理器
 * 因此虽然这个Demo不完善，但是当调用的方法中没有@Transactional注解，仍能正常运行
 * @author sxh
 * @date 2021/11/18
 */
// @Configuration
public class MyCustomTransactionManagementConfigurer implements TransactionManagementConfigurer {
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager();
    }
}
