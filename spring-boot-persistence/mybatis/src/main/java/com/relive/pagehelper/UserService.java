package com.relive.pagehelper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此类用于演示 PageHelper 错误使用造成的SQL异常
 *
 * @author: ReLive
 * @date: 2023/3/5 16:17
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public PageInfo getUserList() {
        PageHelper.startPage(1, 2);

        //模拟 PageHelper.startPage() 与 Mapper 之间抛出异常
        int i = 1 / 0;

        List<User> users = userMapper.selectList(null);
        return new PageInfo(users);
    }

    public User getUser() {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().last("limit 1"));
    }
}
