package com.desafio.itau.statistics.application.usecases.validators;

import com.desafio.itau.statistics.application.usecases.annotations.NonNegative;
import com.desafio.itau.statistics.domain.constants.ErrorCodeMessageConstants;
import com.desafio.itau.statistics.domain.constants.ErrorMessagesConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class NonNegativeValidator implements ConstraintValidator<NonNegative, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            AddCustomError.add(
                    context,
                    ErrorCodeMessageConstants.NON_NEGATIVE_VIOLATION,
                    ErrorMessagesConstants.TRANSACTION_VALUE_CANNOT_BE_NEGATIVE
            );
            return false;
        }
        return true;
    }

}
