package com.mikhailkarpov.vidly.vidlyapi.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        //todo add MessageResolver

        org.passay.PasswordValidator validator = new org.passay.PasswordValidator(Arrays.asList(
                //at least one alphabetical character
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),

                //at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                //at least one special character
                new CharacterRule(EnglishCharacterData.Special, 1),

                //no whitespaces
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
