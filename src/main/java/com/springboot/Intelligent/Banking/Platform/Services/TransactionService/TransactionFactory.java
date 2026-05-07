package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionMode;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TransactionFactory {

    public Transaction createTransaction(
            Account account,
            double amount,
            TransactionType type
    ) {

        Transaction transaction = new Transaction();

        transaction.setTransactionAmount(amount);
        transaction.setTransactionType(type);
        transaction.setTransactionMode(TransactionMode.UPI);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionTime(LocalDateTime.now());

        transaction.setAccount(account);

        return transaction;
    }
}