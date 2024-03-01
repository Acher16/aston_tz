package com.example.aston_tz.repository;

import com.example.aston_tz.entity.Operation;
import com.example.aston_tz.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccount_NumberAccount(UUID numberAccount);
}
