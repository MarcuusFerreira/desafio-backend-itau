package com.desafio.itau.statistics.application.usecases.interfaces;

import com.desafio.itau.statistics.domain.entities.transactions.Transaction;

public interface SaveTransaction {

    void save(Transaction transaction);

}
