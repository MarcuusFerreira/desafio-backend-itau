package com.desafio.itau.statistics.domain.entities.transactions;

public class TransactionCannotBeAFutureDateException extends RuntimeException {
    public TransactionCannotBeAFutureDateException(String message) {
        super(message);
    }
}
