package Part1_事务;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 编程级事务
 * @author sxh
 * @date 2020/1/2
 */
public class ProgrammaticTransaction {
    //1、注入事务管理器对象
    @Autowired
    private PlatformTransactionManager txManager;

}
