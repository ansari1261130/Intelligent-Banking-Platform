package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransferDto.TransferRequestDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.TransactionRepository;
import com.springboot.Intelligent.Banking.Platform.Services.Common.AccountValidationService;
import com.springboot.Intelligent.Banking.Platform.Services.TransactionService.TransactionFactory;
import com.springboot.Intelligent.Banking.Platform.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransferTransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;
    private final TransactionMapper transactionMapper;
    private final AccountValidationService validationService;


    @Async
    @Transactional
    public CompletableFuture<TransactionDto> execute(
            TransferRequestDto request
    ) {

        validationService.validateAmount(
                request.getAmount()
        );

        Account sourceAccount =
                validationService.getAccount(
                        request.getSourceAccountNumber()
                );

        Account destinationAccount =
                validationService.getAccount(
                        request.getDestinationAccountNumber()
                );

        if(sourceAccount.getAccountBalance() < request.getAmount()){
            throw new RuntimeException(
                    "Insufficient balance"
            );
        }

        // Debit
        sourceAccount.setAccountBalance(
                sourceAccount.getAccountBalance()
                        - request.getAmount()
        );

        // Credit
        destinationAccount.setAccountBalance(
                destinationAccount.getAccountBalance()
                        + request.getAmount()
        );

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction =
                transactionFactory.createTransaction(
                        sourceAccount,
                        request.getAmount(),
                        TransactionType.TRANSFER
                );

        Transaction saved =
                transactionRepository.save(transaction);

        return CompletableFuture.completedFuture(
                transactionMapper.toDto(
                        saved,
                        sourceAccount
                )
        );
    }
}