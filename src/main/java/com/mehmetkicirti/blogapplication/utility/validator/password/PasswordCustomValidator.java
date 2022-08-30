package com.mehmetkicirti.blogapplication.utility.validator.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordCustomValidator implements ConstraintValidator<PasswordValidator, String> {
    private List<String> weakPasswords;
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("1234","password","qwerty");
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password != null && password.matches(REGEX_PASSWORD) && (!weakPasswords.contains(password));
    }
}
