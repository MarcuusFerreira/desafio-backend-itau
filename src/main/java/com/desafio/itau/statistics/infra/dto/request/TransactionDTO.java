package com.desafio.itau.statistics.infra.dto.request;

import com.desafio.itau.statistics.application.usecases.annotations.DateCannotBeInFuture;
import com.desafio.itau.statistics.application.usecases.annotations.NonNegative;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(
        @NotNull(message = "Field value cannot be null")
        @NonNegative
        BigDecimal value,
        @NotNull(message = "Field datetime cannot be null")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @DateCannotBeInFuture
        OffsetDateTime datetime
) {
}
