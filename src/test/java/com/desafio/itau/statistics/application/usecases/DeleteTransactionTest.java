package com.desafio.itau.statistics.application.usecases;

import com.desafio.itau.statistics.application.gateways.TransactionRepositoryInterface;
import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;
import com.desafio.itau.statistics.infra.controller.transaction.TransactionRestController;
import com.desafio.itau.statistics.infra.gateways.TransactionRepositoryInMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteTransactionTest {

    @Mock
    private DeleteTransaction deleteTransaction;

    @Spy
    private TransactionRepositoryInMemory transactionRepository;

    @InjectMocks
    private TransactionRestController transactionRestController;

    @Test
    public void deleteTransaction() {
        ResponseEntity<?> response = transactionRestController.delete();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(transactionRepository.findAll().isEmpty());
        verify(deleteTransaction, times(1)).delete();
    }
}
