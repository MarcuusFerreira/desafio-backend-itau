package com.desafio.itau.statistics.config;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.impl.DeleteTransactionImpl;
import com.desafio.itau.statistics.application.usecases.impl.SaveTransactionImpl;
import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;
import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.infra.gateways.TransactionRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfiguration {

    @Bean
    public TransactionRepositoryInterface transactionRepository() {
        return new TransactionRepositoryInMemory();
    }

    @Bean
    public SaveTransaction saveTransaction(TransactionRepositoryInterface transactionRepository) {
        return new SaveTransactionImpl(transactionRepository);
    }

    @Bean
    public DeleteTransaction deleteTransaction(TransactionRepositoryInterface transactionRepository) {
        return new DeleteTransactionImpl(transactionRepository);
    }

}
