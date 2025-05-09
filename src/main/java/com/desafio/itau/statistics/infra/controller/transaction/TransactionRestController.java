package com.desafio.itau.statistics.infra.controller.transaction;

import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;
import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.infra.dto.request.TransactionDTO;
import com.desafio.itau.statistics.infra.dto.response.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Transaction")
@RequestMapping(path = "/transacao", produces = "application/json")
public class TransactionRestController {

    private final SaveTransaction saveTransaction;
    private final DeleteTransaction deleteTransaction;

    public TransactionRestController(SaveTransaction saveTransaction, DeleteTransaction deleteTransaction) {
        this.saveTransaction = saveTransaction;
        this.deleteTransaction = deleteTransaction;
    }

    @PostMapping()
    @Operation(
            summary = "Save transaction",
            description = "Save transaction in memory",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                    )
            }
    )
    public ResponseEntity<?> save(@RequestBody @Valid TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO.value(), transactionDTO.datetime());
        saveTransaction.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(
            summary = "Delete transactions",
            description = "Delete all saved transactions",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                    )
            }
    )
    public ResponseEntity<?> delete() {
        deleteTransaction.delete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
