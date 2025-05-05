package com.desafio.itau.statistics.application.gateways;

import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

import java.time.OffsetDateTime;
import java.util.Map;

public interface TransactionRepositoryInterface {

    void save(Transaction transaction);

    void delete();

    Map<OffsetDateTime, Transaction> findAll();

    Map<OffsetDateTime, Transaction> findByDateGreaterThan(OffsetDateTime date);

}
