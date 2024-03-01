package com.example.aston_tz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private LocalDateTime localDateTime; /*или ZonedDateTime ?*/
    @NonNull
    @Enumerated(EnumType.STRING)
    private Operation operation;
}
