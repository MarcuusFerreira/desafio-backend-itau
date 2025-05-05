package com.desafio.itau.statistics.config;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.impl.CalculateStatisticsImpl;
import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticsConfiguration {

    @Value("${application.seconds}")
    private Integer seconds;

    @Bean
    public CalculateStatistics calculateStatistics(TransactionRepositoryInterface transactionRepository) {
        return new CalculateStatisticsImpl(seconds, transactionRepository);
    }

}
