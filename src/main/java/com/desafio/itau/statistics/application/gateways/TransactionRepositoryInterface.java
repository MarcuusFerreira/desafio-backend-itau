package com.desafio.itau.statistics.application.gateways;

import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.domain.entities.transactions.TransactionKey;

import java.time.OffsetDateTime;
import java.util.Map;

public interface TransactionRepositoryInterface {

    void save(Transaction transaction);

    void delete();

    Map<TransactionKey, Transaction> findAll();

    Map<TransactionKey, Transaction> findByDateGreaterThan(OffsetDateTime date);

}
