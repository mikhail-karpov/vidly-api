package com.mikhailkarpov.vidly.vidlyapi.validation;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationRequestValidator implements ConstraintValidator<EqualPassword, RegistrationRequest> {

   @Override
   public void initialize(EqualPassword constraint) {
   }

   @Override
   public boolean isValid(RegistrationRequest obj, ConstraintValidatorContext context) {
      String password = obj.getPassword();
      String matchingPassword = obj.getMatchingPassword();

      if (password == null || matchingPassword == null)
         return false;

      return obj.getPassword().equals(matchingPassword);
   }
}
