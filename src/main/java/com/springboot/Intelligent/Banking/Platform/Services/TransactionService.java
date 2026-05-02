package com.springboot.Intelligent.Banking.Platform.Services;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionMode;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Transactional
    public ResponseEntity<TransactionDto> depositMoney(TransactionRequestDto request) {
        try {
            if (request.getAmount() <= 0) {
                return ResponseEntity.badRequest().build();
            }

            Account account = accountRepository
                    .findByAccountNumber(request.getAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            account.setAccountBalance(account.getAccountBalance() + request.getAmount());
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setTransactionAmount(request.getAmount());
            transaction.setTransactionType(TransactionType.DEPOSIT);
            transaction.setTransactionMode(TransactionMode.UPI);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction.setTransactionDate(LocalDate.now());
            transaction.setTransactionTime(LocalDateTime.now());

            transaction.setAccount(account);

            Transaction savedTxn = transactionRepository.save(transaction);

            TransactionDto response = new TransactionDto(
                    savedTxn.getTransactionId(),
                    account.getAccountNumber(),
                    savedTxn.getTransactionAmount(),
                    account.getAccountBalance(),
                    savedTxn.getTransactionMode(),
                    savedTxn.getTransactionType(),
                    savedTxn.getTransactionStatus(),
                    savedTxn.getTransactionDate(),
                    savedTxn.getTransactionTime()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
