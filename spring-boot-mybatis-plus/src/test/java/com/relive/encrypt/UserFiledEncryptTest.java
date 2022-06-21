package com.relive.encrypt;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author: ReLive
 * @date: 2022/6/21 11:19 上午
 */
@MybatisPlusTest
@RunWith(SpringRunner.class)
public class UserFiledEncryptTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @Sql(value = "/create.sql")
    public void encryptAndDecryptFiledTest() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("admin");
        user.setPassword("123456");
        userMapper.insert(user);
        User resultUser = userMapper.selectOne(new LambdaQueryWrapper<User>().last("limit 1"));
        Assert.assertEquals(user, resultUser);
    }
}
