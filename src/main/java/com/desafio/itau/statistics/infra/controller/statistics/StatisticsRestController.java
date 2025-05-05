package com.desafio.itau.statistics.infra.controller.statistics;

import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import com.desafio.itau.statistics.domain.entities.statistics.Statistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "estatistica")
public class StatisticsRestController {

    private final CalculateStatistics calculateStatistics;

    public StatisticsRestController(CalculateStatistics calculateStatistics) {
        this.calculateStatistics = calculateStatistics;
    }

    @GetMapping
    public ResponseEntity<Statistics> getStatistics() {
        Statistics statistics = calculateStatistics.calculate();
        return ResponseEntity.ok(statistics);
    }

}
