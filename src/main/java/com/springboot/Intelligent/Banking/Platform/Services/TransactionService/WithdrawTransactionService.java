package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionMode;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.TransactionRepository;
import com.springboot.Intelligent.Banking.Platform.Services.Common.AccountValidationService;
import com.springboot.Intelligent.Banking.Platform.Services.CardService.CardValidationService;
import com.springboot.Intelligent.Banking.Platform.Services.TransactionService.TransactionFactory;
import com.springboot.Intelligent.Banking.Platform.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class WithdrawTransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionFactory transactionFactory;

    private final TransactionMapper transactionMapper;

    private final AccountValidationService validationService;

    private final CardValidationService cardValidationService;


    @Async
    @Transactional
    public CompletableFuture<TransactionDto> execute(
            TransactionRequestDto request
    ) {

        // Step 1
        validationService.validateAmount(
                request.getAmount()
        );

        // Step 2
        Account account =
                validationService.getAccount(
                        request.getAccountNumber()
                );

        // Step 3
        Transaction transaction =
                transactionFactory.createTransaction(
                        account,
                        request.getAmount(),
                        TransactionType.WITHDRAW,
                        TransactionMode.valueOf(
                                request.getTransactionMode()
                                        .toUpperCase()
                        ),
                        TransactionStatus.PENDING
                );

        transaction =
                transactionRepository.save(
                        transaction
                );


        // Step 4 → Card validation
        if(request.getTransactionMode()
                .equalsIgnoreCase("CARD")) {

            boolean isPinValid =
                    cardValidationService.validatePin(
                            account,
                            request.getAtmPin()
                    );

            if(!isPinValid){

                transaction.setTransactionStatus(
                        TransactionStatus.FAILED
                );

                transactionRepository.save(
                        transaction
                );

                throw new RuntimeException(
                        "Invalid ATM PIN"
                );
            }
        }


        // Step 5 → Balance check
        if(request.getAmount()
                > account.getAccountBalance()){

            transaction.setTransactionStatus(
                    TransactionStatus.FAILED
            );

            transactionRepository.save(
                    transaction
            );

            throw new RuntimeException(
                    "Insufficient balance"
            );
        }


        // Step 6 → Withdraw
        account.setAccountBalance(
                account.getAccountBalance()
                        - request.getAmount()
        );

        accountRepository.save(
                account
        );


        // Step 7 → Success
        transaction.setTransactionStatus(
                TransactionStatus.SUCCESS
        );

        Transaction savedTransaction =
                transactionRepository.save(
                        transaction
                );


        // Step 8 → Response
        return CompletableFuture.completedFuture(
                transactionMapper.toDto(
                        savedTransaction,
                        account
                )
        );
    }
}