package com.example.aston_tz.controller;

import com.example.aston_tz.dto.*;
import com.example.aston_tz.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 29.02.2024 что возвращать на фронт
@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping
    public ResponseEntity<AccountCreateDto> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.createAccount(accountCreateDto));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDepositDto> deposit(@RequestBody TransactionDepositDto transactionDepositDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.deposit(transactionDepositDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionWithdrawDto> withdraw(@RequestBody TransactionWithdrawDto transactionWithdrawDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.withdraw(transactionWithdrawDto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionTransferDto> transfer(@RequestBody TransactionTransferDto transactionTransferDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.transfer(transactionTransferDto));
    }

    @GetMapping
    public ResponseEntity<List<AccountsAllDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.findAll());
    }

}
