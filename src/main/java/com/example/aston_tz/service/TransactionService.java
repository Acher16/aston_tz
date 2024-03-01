package com.example.aston_tz.service;

import com.example.aston_tz.dto.TransactionDto;
import com.example.aston_tz.entity.Account;
import com.example.aston_tz.entity.Operation;
import com.example.aston_tz.entity.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    void depositLog(Account account, BigDecimal amount, LocalDateTime now, Operation deposit);

    void withdrawLog(Account account, BigDecimal amount, LocalDateTime now, Operation withdraw);

    List<TransactionDto> findAllTransactionsByAccountNumber(UUID numberAccount);
}
