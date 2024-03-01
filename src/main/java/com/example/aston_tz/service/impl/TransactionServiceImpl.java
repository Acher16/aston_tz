package com.example.aston_tz.service.impl;

import com.example.aston_tz.dto.TransactionDto;
import com.example.aston_tz.entity.Account;
import com.example.aston_tz.entity.Operation;
import com.example.aston_tz.entity.Transaction;
import com.example.aston_tz.repository.TransactionRepository;
import com.example.aston_tz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void depositLog(Account account, BigDecimal amount, LocalDateTime date, Operation operation) {
        transactionRepository.save(
                new Transaction(account, amount, date, operation)
        );
    }

    @Override
    @Transactional
    public void withdrawLog(Account account, BigDecimal amount, LocalDateTime date, Operation operation) {
        transactionRepository.save(
                new Transaction(account, amount, date, operation)
        );
    }

    @Override
    public List<TransactionDto> findAllTransactionsByAccountNumber(UUID numberAccount) {
        return transactionRepository.findAllByAccount_NumberAccount(numberAccount)
                .stream().map(
                        transaction -> TransactionDto.builder()
                                .localDateTime(transaction.getLocalDateTime())
                                .operation(transaction.getOperation())
                                .amount(transaction.getAmount())
                                .build()
                ).toList();
    }
}
