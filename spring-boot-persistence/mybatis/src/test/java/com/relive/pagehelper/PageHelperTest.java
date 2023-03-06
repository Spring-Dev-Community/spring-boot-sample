package com.relive.pagehelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author: ReLive
 * @date: 2023/3/5 16:25
 */
@SpringBootTest
@Sql("/user.sql")
public class PageHelperTest {

    @Autowired
    private UserService userService;

    /**
     * 测试当PageHelper使用不当引起多个limit关键字的SQL错误
     */
    @Test
    public void badSqlGrammarExceptionWhenWrongUseOfPagehelper() {
        Assertions.assertThrows(ArithmeticException.class, () -> userService.getUserList());
        Assertions.assertThrows(BadSqlGrammarException.class, () -> userService.getUser());
    }
}
