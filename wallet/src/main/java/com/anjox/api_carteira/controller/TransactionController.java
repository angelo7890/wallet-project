package com.anjox.api_carteira.controller;

import com.anjox.api_carteira.dto.RequestTransactionDTO;
import com.anjox.api_carteira.dto.ResponseTransactionDTO;
import com.anjox.api_carteira.entity.TransactionsEntity;
import com.anjox.api_carteira.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<ResponseTransactionDTO> transaction(@RequestBody @Valid RequestTransactionDTO dto){
        TransactionsEntity transaction = transactionService.createTransaction(dto);
        ResponseTransactionDTO response = new ResponseTransactionDTO(
                transaction.getId(),
                transaction.getValorTransacao(),
                transaction.getMenssagem(),
                transaction.getDateTransacao()
        );
        return ResponseEntity.ok(response);
    }
}
