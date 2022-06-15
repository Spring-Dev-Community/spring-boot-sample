package com.relive.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.function.Predicate;

@Component
public class UserValidation<T extends Annotation> implements ConstraintValidator<T, UserSaveDTO> {

    protected Predicate<UserSaveDTO> predicate = c -> true;


    @Override
    public boolean isValid(UserSaveDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return predicate.test(user);
    }


    /**
     * 判断用户名是否唯一
     */
    public static class UniqueUserValidator extends UserValidation<UniqueUser> {
        public void initialize(UniqueUser constraintAnnotation) {
            predicate = c -> !"admin".equals(c.getUsername());
        }
    }

}
