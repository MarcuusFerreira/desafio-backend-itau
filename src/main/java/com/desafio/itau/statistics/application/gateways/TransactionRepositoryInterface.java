package com.desafio.itau.statistics.application.gateways;

import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

public interface TransactionRepositoryInterface {

    void save(Transaction transaction);

    void delete();

}
