package com.desafio.itau.statistics.infra.gateways;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<OffsetDateTime, Transaction> findAll() {
        return this.transactions;
    }

    @Override
    public Map<OffsetDateTime, Transaction> findByDateGreaterThan(OffsetDateTime date) {
        return this.transactions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAfter(date))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
