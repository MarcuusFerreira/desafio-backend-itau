package com.desafio.itau.statistics.infra.gateways;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class TransactionRepositoryInMemory implements TransactionRepositoryInterface {

    private final Map<OffsetDateTime, Transaction> transactions = new HashMap<>();

    @Override
    public void save(Transaction transaction) {
        transactions.put(transaction.getDatetime(), transaction);
    }

    @Override
    public void delete() {
        transactions.clear();
    }

    public Map<OffsetDateTime, Transaction> getTransactions() {
        return this.transactions;
    }
}
