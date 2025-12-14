package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.annotations.ValidAge;

public class ValidAgeValidator implements ConstraintValidator<ValidAge,Integer> {

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context){
        if(age==null||age<0)  return false;
        return age >= 18 && age <= 65;
    }

}