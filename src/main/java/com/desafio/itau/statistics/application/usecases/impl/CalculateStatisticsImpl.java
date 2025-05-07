package com.desafio.itau.statistics.application.usecases.impl;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import com.desafio.itau.statistics.domain.entities.statistics.Statistics;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.domain.entities.transactions.TransactionKey;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculateStatisticsImpl implements CalculateStatistics {

    private final Integer seconds;
    private final TransactionRepositoryInterface transactionRepository;

    private static final Integer DECIMAL_PLACES = 2;

    public CalculateStatisticsImpl(Integer seconds, TransactionRepositoryInterface transactionRepository) {
        this.seconds = seconds;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Statistics calculate() {
        OffsetDateTime time = OffsetDateTime.now().minusSeconds(seconds);
        System.out.println(time);
        Map<TransactionKey, Transaction> transactionMap = transactionRepository.findByDateGreaterThan(time);
        List<Transaction> transactions = new ArrayList<Transaction>(transactionMap.values());
        if (transactions.isEmpty()) {
            return new Statistics(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        int count = transactions.size();
        BigDecimal sum = transactions.stream()
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), DECIMAL_PLACES, RoundingMode.HALF_UP);
        BigDecimal min = transactions.stream()
                .map(Transaction::getValue)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transactions.stream()
                .map(Transaction::getValue)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return new Statistics(count, sum, avg, min, max);
    }

}
