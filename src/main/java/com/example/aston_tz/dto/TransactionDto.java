package com.example.aston_tz.dto;

import com.example.aston_tz.entity.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDto {
    private BigDecimal amount;
    private LocalDateTime localDateTime;
    private Operation operation;
}
