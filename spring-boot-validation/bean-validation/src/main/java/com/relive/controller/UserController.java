package com.relive.controller;

import com.relive.validation.UniqueUser;
import com.relive.validation.UserSaveDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ReLive
 * @date: 2022/5/18 9:05 下午
 */
@Validated
@RestController
public class UserController {

    @PostMapping("/user")
    public String addUser(@UniqueUser @RequestBody UserSaveDTO userSaveDTO) {
        return "add user success";
    }
}
