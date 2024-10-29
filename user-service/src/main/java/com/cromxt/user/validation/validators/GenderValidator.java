package com.cromxt.user.validation.validators;

import com.cromxt.user.validation.GenderValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidation,String> {
    @Override
    public void initialize(GenderValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if(string!=null){
            return string.equalsIgnoreCase("Male") || string.equalsIgnoreCase("Female");
        }
        return false;
    }
}
