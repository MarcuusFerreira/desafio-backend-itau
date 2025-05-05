package com.desafio.itau.statistics.application.usecases.impl;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

public class SaveTransactionImpl implements SaveTransaction {

    private final TransactionRepositoryInterface transactionRepository;

    public SaveTransactionImpl(TransactionRepositoryInterface transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
