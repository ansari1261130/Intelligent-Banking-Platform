package com.springboot.Intelligent.Banking.Platform.Entities.Transaction;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Table(name = "transactions")
@Entity
@ToString
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long transactionId;

    private double transactionAmount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMode transactionMode;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    private LocalDateTime transactionTime;


    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
