package com.example.aston_tz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID numberAccount; /*номер счета создается автомотически - что использовать в качестве номера счет?*/
    private String name;
    private String pin;
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) /*нужно ли удолять тразакции при удалении счета?*/
    private List<Transaction> transactions;
}
