package com.example.aston_tz.controller;

import com.example.aston_tz.dto.TransactionDto;
import com.example.aston_tz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> findAllTransactionsByAccountNumber(@RequestBody UUID numberAccount) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.findAllTransactionsByAccountNumber(numberAccount));
    }
}
