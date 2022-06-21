package com.relive.encrypt;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: ReLive
 * @date: 2022/6/21 10:58 上午
 */
@Data
@TableName(value = "db_user", autoResultMap = true)
public class User {

    @TableId(type = IdType.INPUT)
    private String id;
    private String username;
    @TableField(typeHandler = EncryptTypeHandler.class)
    private String password;
}
