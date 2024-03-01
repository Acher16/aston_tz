package com.example.aston_tz.service.impl;

import com.example.aston_tz.dto.*;
import com.example.aston_tz.entity.Account;
import com.example.aston_tz.entity.Operation;
import com.example.aston_tz.exception.AccountNotFoundException;
import com.example.aston_tz.exception.InvalidPinException;
import com.example.aston_tz.repository.AccountRepository;
import com.example.aston_tz.service.AccountService;
import com.example.aston_tz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;
    @Override
    @Transactional
    public AccountCreateDto createAccount(AccountCreateDto accountCreateDto) {
        accountRepository.save(
                modelMapper.map(accountCreateDto, Account.class)
        );
        return accountCreateDto;
    }

    @Override
    @Transactional
    public TransactionDepositDto deposit(TransactionDepositDto transactionDepositDto) {
        UUID numberAccount = transactionDepositDto.getNumberAccount();
        BigDecimal amount = transactionDepositDto.getAmount();
        Account account = accountRepository.findById(numberAccount)
                .map(acc -> {
                    acc.setBalance(acc.getBalance().add(amount));
                    return acc;
                })
                .orElseThrow(() -> new AccountNotFoundException("account not found"));
        transactionService.depositLog(account, amount, LocalDateTime.now(), Operation.DEPOSIT);
        return transactionDepositDto;
    }

    @Override
    @Transactional
    public TransactionWithdrawDto withdraw(TransactionWithdrawDto transactionWithdrawDto) {
        UUID numberAccount = transactionWithdrawDto.getNumberAccount();
        BigDecimal amount = transactionWithdrawDto.getAmount();
        String pin = transactionWithdrawDto.getPin();
        Account account = accountRepository.findById(numberAccount)
                .orElseThrow(() -> new AccountNotFoundException("account not found"));
        if (!account.getPin().equals(pin))
            throw new InvalidPinException("pin is not available");
        account.setBalance(account.getBalance().subtract(amount));
        transactionService.withdrawLog(account, amount, LocalDateTime.now(), Operation.WITHDRAW);
        return transactionWithdrawDto;
    }

    @Override
    @Transactional
    public TransactionTransferDto transfer(TransactionTransferDto transactionTransferDto) {
        UUID fromNumberAccount = transactionTransferDto.getFromNumberAccount();
        UUID toNumberAccount = transactionTransferDto.getToNumberAccount();
        BigDecimal amount = transactionTransferDto.getAmount();
        String pin = transactionTransferDto.getPin();
        withdraw(
                TransactionWithdrawDto.builder()
                        .numberAccount(fromNumberAccount)
                        .amount(amount)
                        .pin(pin)
                        .build()
                );
        deposit(
                TransactionDepositDto.builder()
                        .numberAccount(toNumberAccount)
                        .amount(amount)
                        .build()
                );
        return transactionTransferDto;
    }

    @Override
    public List<AccountsAllDto> findAll() {
        return accountRepository.findAll().stream().map(account ->
                AccountsAllDto.builder()
                    .numberAccounts(account.getNumberAccount())
                    .name(account.getName())
                    .balance(account.getBalance())
                    .build()
        ).toList();
    }

}
