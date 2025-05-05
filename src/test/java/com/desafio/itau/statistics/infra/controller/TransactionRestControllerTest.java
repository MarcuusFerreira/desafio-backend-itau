package com.desafio.itau.statistics.infra.controller;

import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;
import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.infra.controller.transaction.TransactionRestController;
import com.desafio.itau.statistics.infra.gateways.TransactionRepositoryInMemory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionRestController.class)
public class TransactionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaveTransaction saveTransaction;

    @MockBean
    private DeleteTransaction deleteTransaction;

    @SpyBean
    private TransactionRepositoryInMemory transactionRepository;

    @Test
    void createTransactionShouldReturn201Created() throws Exception {
        String requestBody = "{\"value\":100.0,\"datetime\":\"2023-08-01T10:00:00.000Z\"}";
        doNothing().when(saveTransaction).save(any());
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    void createTransactionWithNegativeValuesShouldReturn422() throws Exception {
        String requestBody = "{\"value\":-100.0,\"datetime\":\"2023-08-01T10:00:00.000Z\"}";
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createTransactionWithFutureDateShouldReturn422() throws Exception {
        String requestBody = "{\"value\":100.0,\"datetime\":\"3000-08-01T10:00:00.000Z\"}";

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createTransactionWithNoPayloadShouldReturn400() throws Exception {
        String requestBody = "";
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTransactionWithInvalidPayloadShouldReturn400() throws Exception {
        String requestBody = "{\"aaaaaaaa\":100.0,\"bbbbbbbb\":\"3000-08-01T10:00:00.000Z\"}";

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTransactionShouldReturn200() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
        verify(deleteTransaction, times(1)).delete();
        assertTrue(transactionRepository.getTransactions().isEmpty());
    }

}
