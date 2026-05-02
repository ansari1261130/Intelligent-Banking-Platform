package com.springboot.Intelligent.Banking.Platform.mapper;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(
            Transaction txn,
            Account account
    ) {

        return new TransactionDto(
                txn.getTransactionId(),
                account.getAccountNumber(),
                txn.getTransactionAmount(),
                account.getAccountBalance(),
                txn.getTransactionMode(),
                txn.getTransactionType(),
                txn.getTransactionStatus(),
                txn.getTransactionDate(),
                txn.getTransactionTime()
        );
    }
}