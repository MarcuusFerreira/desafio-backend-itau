package com.desafio.itau.statistics.infra.controller.statistics;

import com.desafio.itau.statistics.application.usecases.interfaces.CalculateStatistics;
import com.desafio.itau.statistics.domain.entities.statistics.Statistics;
import com.desafio.itau.statistics.infra.dto.response.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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
    @Operation(
            summary = "Return statistics",
            description = "Returns statistics for the last few minutes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Statistics.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                    )
            }
    )
    public ResponseEntity<Statistics> getStatistics() {
        Statistics statistics = calculateStatistics.calculate();
        return ResponseEntity.ok(statistics);
    }

}
