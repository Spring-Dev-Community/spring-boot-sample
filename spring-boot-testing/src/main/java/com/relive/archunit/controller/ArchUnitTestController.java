package com.relive.archunit.controller;

import com.relive.archunit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ReLive
 * @date: 2022/12/18 23:02
 */
@RestController
public class ArchUnitTestController {

    @Autowired
    private UserService userService;
}
