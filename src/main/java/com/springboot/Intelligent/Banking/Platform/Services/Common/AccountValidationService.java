package com.springboot.Intelligent.Banking.Platform.Services.Common;

import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountValidationService {

    private final AccountRepository accountRepository;


    public void validateAmount(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid transaction amount");
        }
    }


    public Account getAccount(Long accountNumber) {
        return accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new RuntimeException("Account not found")
                );
    }
}