package com.desafio.itau.statistics.application.usecases.annotations;

import com.desafio.itau.statistics.application.usecases.validators.NonNegativeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {NonNegativeValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNegative {

    String message() default "Value cannot be negative";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
