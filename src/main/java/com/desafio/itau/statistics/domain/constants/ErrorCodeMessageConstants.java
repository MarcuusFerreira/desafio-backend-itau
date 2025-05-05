package com.desafio.itau.statistics.domain.constants;

public enum ErrorCodeMessageConstants {

    NON_NEGATIVE_VIOLATION("NON_NEGATIVE_VIOLATION"),
    FUTURE_DATE_VIOLATION("FUTURE_DATE_VIOLATION");

    private final String value;

    ErrorCodeMessageConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
