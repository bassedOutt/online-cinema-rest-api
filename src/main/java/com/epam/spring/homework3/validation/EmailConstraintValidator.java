package com.epam.spring.homework3.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

    private final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}
