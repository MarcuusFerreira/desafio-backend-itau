package com.desafio.itau.statistics.application.usecases.annotations;

import com.desafio.itau.statistics.application.usecases.validators.DateCannotBeInFutureValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DateCannotBeInFutureValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateCannotBeInFuture {

    String message() default "Date cannot be in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
