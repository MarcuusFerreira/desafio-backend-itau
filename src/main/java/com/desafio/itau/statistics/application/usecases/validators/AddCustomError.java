package com.desafio.itau.statistics.application.usecases.validators;

import com.desafio.itau.statistics.domain.constants.ErrorCodeMessageConstants;
import com.desafio.itau.statistics.domain.constants.ErrorMessagesConstants;
import jakarta.validation.ConstraintValidatorContext;

public class AddCustomError {

    public static void add(ConstraintValidatorContext context, ErrorCodeMessageConstants errorCode, ErrorMessagesConstants message) {
        String errorTemplate = errorCode.getValue() + ":" + message.getValue();
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorTemplate).addConstraintViolation();
    }
}
