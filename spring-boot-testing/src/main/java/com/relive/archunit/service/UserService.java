package com.relive.archunit.service;

import com.relive.archunit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ReLive
 * @date: 2023/2/22 19:02
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
}
