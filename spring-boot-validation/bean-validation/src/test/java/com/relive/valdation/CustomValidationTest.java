package com.relive.valdation;

import com.relive.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author: ReLive
 * @date: 2022/5/18 9:09 下午
 */
@WebMvcTest(UserController.class)
public class CustomValidationTest {

    @Autowired
    protected MockMvc mvc;

    @Test
    public void validationTest() throws Exception {
        this.mvc.perform(post("/user").content("{\"username\":\"admin\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals("addUser.userSaveDTO: 用户名称不允许与现存用户重复", result.getResolvedException().getMessage()));
    }
}
