package com.desafio.itau.statistics.application.usecases.validators;

import com.desafio.itau.statistics.application.usecases.annotations.DateCannotBeInFuture;
import com.desafio.itau.statistics.domain.constants.ErrorCodeMessageConstants;
import com.desafio.itau.statistics.domain.constants.ErrorMessagesConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.OffsetDateTime;

public class DateCannotBeInFutureValidator implements ConstraintValidator<DateCannotBeInFuture, OffsetDateTime> {

    @Override
    public boolean isValid(OffsetDateTime date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }
        if (date.isAfter(OffsetDateTime.now())) {
            AddCustomError.add(
                    context,
                    ErrorCodeMessageConstants.FUTURE_DATE_VIOLATION,
                    ErrorMessagesConstants.TRANSACTION_DATA_CANNOT_BE_IN_THE_FUTURE
            );
            return false;
        }
        return true;
    }

}
