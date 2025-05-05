package com.desafio.itau.statistics.domain.entities.transactions;

public class TransactionValueIsNegativeException extends RuntimeException {
    public TransactionValueIsNegativeException(String message) {
        super(message);
    }
}
