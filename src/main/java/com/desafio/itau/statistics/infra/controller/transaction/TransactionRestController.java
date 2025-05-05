package com.desafio.itau.statistics.infra.controller.transaction;

import com.desafio.itau.statistics.application.usecases.interfaces.DeleteTransaction;
import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.infra.dto.request.TransactionDTO;
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
    public ResponseEntity<?> save(@RequestBody @Valid TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO.value(), transactionDTO.datetime());
        saveTransaction.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        deleteTransaction.delete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
