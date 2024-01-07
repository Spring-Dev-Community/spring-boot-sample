package com.relive.pagehelper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: ReLive
 * @date: 2023/3/5 16:26
 */
@TableName("db_user")
public class User {
    @TableId(type = IdType.INPUT)
    private String id;
    private String username;
    private String password;
}
