package com.mehmetkicirti.blogapplication.utility.validator.password;

import com.mehmetkicirti.blogapplication.utility.constant.ValidatorMessageConstants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordCustomValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default ValidatorMessageConstants.VALID_PASSWORD;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
