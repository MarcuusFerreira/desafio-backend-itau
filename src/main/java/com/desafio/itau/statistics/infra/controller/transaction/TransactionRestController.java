package com.desafio.itau.statistics.infra.controller.transaction;

import com.desafio.itau.statistics.application.usecases.interfaces.SaveTransaction;
import com.desafio.itau.statistics.domain.entities.transactions.Transaction;
import com.desafio.itau.statistics.infra.dto.request.TransactionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Transaction")
@RequestMapping(path = "/transacao", produces = "application/json")
public class TransactionRestController {

    private final SaveTransaction saveTransaction;

    public TransactionRestController(SaveTransaction saveTransaction) {
        this.saveTransaction = saveTransaction;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO.value(), transactionDTO.datetime());
        saveTransaction.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
