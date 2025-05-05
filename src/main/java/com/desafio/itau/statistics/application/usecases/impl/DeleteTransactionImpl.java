package com.desafio.itau.statistics.application.usecases.impl;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;

public class DeleteTransactionImpl implements DeleteTransaction {

    private final TransactionRepositoryInterface transactionRepository;

    public DeleteTransactionImpl(TransactionRepositoryInterface transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void delete() {
        transactionRepository.delete();
    }

}
