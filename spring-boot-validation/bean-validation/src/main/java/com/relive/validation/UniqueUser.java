package com.relive.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: ReLive
 * @date: 2022/1/23 1:06 下午
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = UserValidation.UniqueUserValidator.class)
public @interface UniqueUser {

    String message() default "用户名称不允许与现存用户重复";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
