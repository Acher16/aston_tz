package com.example.aston_tz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionWithdrawDto {
    private UUID numberAccount;
    private BigDecimal amount;
    private String pin;
}
