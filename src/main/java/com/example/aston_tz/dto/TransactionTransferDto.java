package com.example.aston_tz.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionTransferDto {
    private UUID fromNumberAccount;
    private UUID toNumberAccount;
    private BigDecimal amount;
    private String pin;
}
