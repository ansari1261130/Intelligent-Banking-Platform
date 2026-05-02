package com.springboot.Intelligent.Banking.Platform.Services;

import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountResponseDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.AccountType;
import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Customer;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public ResponseEntity<AccountResponseDto> createAccount(AccountRequestDto request) {
        try {
            if (request.getAccountBalance() < 1000) {
                return ResponseEntity.badRequest().build();
            }

            AccountType type;
            try {
                type = AccountType.valueOf(request.getAccountType().toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }

            Customer customer = customerRepository
                    .findByCustomerNumber(request.getCustomerNumber())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Account account = new Account();
            account.setAccountBalance(request.getAccountBalance());
            account.setAccountType(type);
            account.setCustomer(customer);

            Account savedAccount = accountRepository.save(account);

            long accountNumber = (long)(Math.random()*100000000000L);
            savedAccount.setAccountNumber(accountNumber);

            accountRepository.save(savedAccount);

            AccountResponseDto response = new AccountResponseDto(
                    account.getAccountNumber(),
                    account.getAccountBalance(),
                    account.getAccountType().name()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
