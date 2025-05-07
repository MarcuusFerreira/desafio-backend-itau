package com.desafio.itau.statistics.application.usecases;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.impl.CalculateStatisticsImpl;
import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import com.desafio.itau.statistics.domain.entities.statistics.Statistics;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.domain.entities.transactions.TransactionKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateStatisctsTest {

    @Mock
    private TransactionRepositoryInterface transactionRepository;

    @InjectMocks
    private CalculateStatisticsImpl calculateStatistics;

    private final Integer seconds = 60;

    @BeforeEach
    void setUp() {
        calculateStatistics = new CalculateStatisticsImpl(seconds, transactionRepository);
    }

    @Test
    void calculateWithTransactionsReturnsCorrectStatistics() {
        Map<TransactionKey, Transaction> transactions = new HashMap<>();
        OffsetDateTime date1 = OffsetDateTime.now().minusSeconds(30);
        OffsetDateTime date2 = OffsetDateTime.now().minusSeconds(20);
        OffsetDateTime date3 = OffsetDateTime.now().minusSeconds(10);
        transactions.put(
                new TransactionKey(date1, 0),
                new Transaction(new BigDecimal("100.00"), date1)
        );
        transactions.put(
                new TransactionKey(date2, 0),
                new Transaction(new BigDecimal("200.00"), date2)
        );
        transactions.put(
                new TransactionKey(date3, 0),
                new Transaction(new BigDecimal("300.00"), date3)
        );
        when(transactionRepository.findByDateGreaterThan(any(OffsetDateTime.class)))
                .thenReturn(transactions);
        Statistics result = calculateStatistics.calculate();
        assertEquals(3, result.getCount());
        assertEquals(new BigDecimal("600.00"), result.getSum());
        assertEquals(new BigDecimal("200.00"), result.getAvg());
        assertEquals(new BigDecimal("100.00"), result.getMin());
        assertEquals(new BigDecimal("300.00"), result.getMax());
    }

    @Test
    void calculateWithNoTransactionsReturnsZero() {
        Map<TransactionKey, Transaction> transactions = new HashMap<>();
        when(transactionRepository.findByDateGreaterThan(any(OffsetDateTime.class)))
                .thenReturn(transactions);
        Statistics result = calculateStatistics.calculate();
        assertEquals(0, result.getCount());
        assertEquals(BigDecimal.ZERO, result.getSum());
        assertEquals(BigDecimal.ZERO, result.getAvg());
        assertEquals(BigDecimal.ZERO, result.getMin());
        assertEquals(BigDecimal.ZERO, result.getMax());
    }
}
