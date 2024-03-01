package com.example.aston_tz.service;

import com.example.aston_tz.dto.*;
import com.example.aston_tz.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountCreateDto createAccount(AccountCreateDto accountCreateDto);
    TransactionDepositDto deposit(TransactionDepositDto transactionDepositDto);
    TransactionWithdrawDto withdraw(TransactionWithdrawDto transactionWithdrawDto);
    TransactionTransferDto transfer(TransactionTransferDto transactionTransferDto);
    List<AccountsAllDto> findAll();
}
