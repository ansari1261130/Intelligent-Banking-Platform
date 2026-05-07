package com.springboot.Intelligent.Banking.Platform.Services;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.TransactionRepository;
import com.springboot.Intelligent.Banking.Platform.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;
    private final TransactionMapper transactionMapper;


    @Transactional
    public TransactionDto depositMoney(TransactionRequestDto request) {

        validateAmount(request.getAmount());

        Account account = getAccount(request.getAccountNumber());

        account.setAccountBalance(
                account.getAccountBalance() + request.getAmount()
        );

        accountRepository.save(account);

        Transaction transaction =
                transactionFactory.createTransaction(
                        account,
                        request.getAmount(),
                        TransactionType.DEPOSIT
                );

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return transactionMapper.toDto(
                savedTransaction,
                account
        );
    }


    @Transactional
    public TransactionDto withdrawMoney(TransactionRequestDto request) {

        validateAmount(request.getAmount());

        Account account = getAccount(request.getAccountNumber());

        if (request.getAmount() > account.getAccountBalance()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setAccountBalance(
                account.getAccountBalance() - request.getAmount()
        );

        accountRepository.save(account);

        Transaction transaction =
                transactionFactory.createTransaction(
                        account,
                        request.getAmount(),
                        TransactionType.WITHDRAW
                );

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return transactionMapper.toDto(
                savedTransaction,
                account
        );
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid transaction amount");
        }
    }


    private Account getAccount(Long accountNumber) {
        return accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new RuntimeException("Account not found")
                );
    }


    public TransactionDto transferMoney(TransactionRequestDto request) {
        Account sourceAccount = getAccount(request.getAccountNumber());
        Account destinationAccount = getAccount(request.getAccountNumber());

        if(sourceAccount.getAccountBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }
        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - request.getAmount());

        destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + request.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = transactionFactory.createTransaction(sourceAccount, request.getAmount(), TransactionType.TRANSFER);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toDto(savedTransaction, sourceAccount);
    }
}