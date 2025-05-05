package com.desafio.itau.statistics.infra.controller;

import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import com.desafio.itau.statistics.domain.entities.statistics.Statistics;
import com.desafio.itau.statistics.infra.controller.statistics.StatisticsRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StatisticsRestController.class)
public class StatisticsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculateStatistics calculateStatistics;

    @Test
    void getStatisticsWhenNoTransactionsReturnsZeroStatistics() throws Exception {
        Statistics emptyStats = new Statistics(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        when(calculateStatistics.calculate()).thenReturn(emptyStats);
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.sum").value(0))
                .andExpect(jsonPath("$.avg").value(0))
                .andExpect(jsonPath("$.min").value(0))
                .andExpect(jsonPath("$.max").value(0));
    }

    @Test
    void getStatisticsWithTransactionsReturnsCorrectStatistics() throws Exception {
        Statistics sampleStats = new Statistics(
                3,
                new BigDecimal("600.00"),
                new BigDecimal("200.00"),
                new BigDecimal("100.00"),
                new BigDecimal("300.00")
        );
        when(calculateStatistics.calculate()).thenReturn(sampleStats);
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(3))
                .andExpect(jsonPath("$.sum").value(600.00))
                .andExpect(jsonPath("$.avg").value(200.00))
                .andExpect(jsonPath("$.min").value(100.00))
                .andExpect(jsonPath("$.max").value(300.00));
    }

}
