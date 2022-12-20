package com.relive.archunit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: ReLive
 * @date: 2022/12/20 19:10
 */
@TableName
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
}
