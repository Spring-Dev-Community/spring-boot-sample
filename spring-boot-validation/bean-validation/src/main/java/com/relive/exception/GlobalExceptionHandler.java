package com.relive.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * @author: ReLive
 * @date: 2022/5/19 12:05 下午
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> error(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
