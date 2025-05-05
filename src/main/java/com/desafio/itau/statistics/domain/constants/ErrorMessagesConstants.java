package com.desafio.itau.statistics.domain.constants;

public enum ErrorMessagesConstants {

    TRANSACTION_VALUE_CANNOT_BE_NEGATIVE("Transaction value cannot be negative"),
    TRANSACTION_DATA_CANNOT_BE_IN_THE_FUTURE("Transaction date cannot be in the future"),
    UNKNOWN_VALIDATION("Unknown validation"),
    REQUEST_BODY_IS_MISSING("Request body is missing"),
    INVALID_JSON_FORMAT("Invalid JSON format"),;

    private final String value;

    ErrorMessagesConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
