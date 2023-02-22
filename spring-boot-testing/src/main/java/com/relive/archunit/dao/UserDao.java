package com.relive.archunit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relive.archunit.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: ReLive
 * @date: 2023/2/22 19:01
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
