package com.desafio.itau.statistics.application.usecases;

import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.infra.controller.transaction.TransactionRestController;
import com.desafio.itau.statistics.infra.dto.request.TransactionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SaveTransactionTest {

    @Mock
    private SaveTransaction saveTransaction;

    @InjectMocks
    private TransactionRestController transactionRestController;

    private TransactionDTO validTransaction;

    @BeforeEach
    void setUp() {
        validTransaction = new TransactionDTO(BigDecimal.valueOf(100.0), OffsetDateTime.now());
    }

    @Test
    void validTransaction() {
        ResponseEntity<?> response = transactionRestController.save(validTransaction);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saveTransaction, times(1)).save(any(Transaction.class));
    }
}
