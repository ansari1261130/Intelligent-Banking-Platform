package com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionMode;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {

    private Long transactionId;
    private Long accountNumber;
    private double transactionAmount;
    private double accountBalance;
    private TransactionMode transactionMode;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionTime;
}
