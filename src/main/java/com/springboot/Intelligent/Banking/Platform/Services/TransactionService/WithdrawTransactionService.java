package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.TransactionRepository;
import com.springboot.Intelligent.Banking.Platform.Services.Common.AccountValidationService;
import com.springboot.Intelligent.Banking.Platform.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WithdrawTransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;
    private final TransactionMapper transactionMapper;
    private final AccountValidationService validationService;


    public TransactionDto execute(
            TransactionRequestDto request
    ) {

        validationService.validateAmount(
                request.getAmount()
        );

        Account account =
                validationService.getAccount(
                        request.getAccountNumber()
                );

        if(request.getAmount() > account.getAccountBalance()){
            throw new RuntimeException(
                    "Insufficient balance"
            );
        }

        account.setAccountBalance(
                account.getAccountBalance()
                        - request.getAmount()
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
}