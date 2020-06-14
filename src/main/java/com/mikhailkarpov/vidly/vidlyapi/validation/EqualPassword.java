package com.mikhailkarpov.vidly.vidlyapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegistrationRequestValidator.class)
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualPassword {

    String message() default "Passwords don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
