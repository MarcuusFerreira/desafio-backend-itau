package com.desafio.itau.statistics.infra.gateways;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.domain.entities.transactions.TransactionKey;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TransactionRepositoryInMemory implements TransactionRepositoryInterface {

    private final NavigableMap<TransactionKey, Transaction> transactions = new ConcurrentSkipListMap<>();
    private final Map<OffsetDateTime, AtomicLong> sequenceCounters = new ConcurrentHashMap<>();

    @Override
    public void save(Transaction transaction) {
        OffsetDateTime datetime = transaction.getDatetime();
        long sequence = sequenceCounters
                .computeIfAbsent(datetime, existingKey -> new AtomicLong())
                .getAndIncrement();
        transactions.put(new TransactionKey(datetime, sequence), transaction);
    }

    @Override
    public void delete() {
        transactions.clear();
        sequenceCounters.clear();
    }

    @Override
    public Map<TransactionKey, Transaction> findAll() {
        return new HashMap<>(this.transactions);
    }

    @Override
    public Map<TransactionKey, Transaction> findByDateGreaterThan(OffsetDateTime date) {
        return this.transactions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getDatetime().isAfter(date))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
