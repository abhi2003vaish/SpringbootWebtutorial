package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidAgeValidator.class})
public @interface ValidAge {
    String message() default "Age should be in range 18 to 65";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
