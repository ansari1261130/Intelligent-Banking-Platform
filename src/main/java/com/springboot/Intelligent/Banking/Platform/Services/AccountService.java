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


    public ResponseEntity<AccountResponseDto> createAccount(
            AccountRequestDto request
    ) {

        try {

            // Minimum balance validation
            if(request.getAccountBalance() < 1000){
                return ResponseEntity.badRequest()
                        .build();
            }

            // ATM PIN validation
            if(request.getAtmPin() < 1000 ||
                    request.getAtmPin() > 9999){

                throw new RuntimeException(
                        "ATM PIN must be 4 digits"
                );
            }

            // Account type validation
            AccountType type =
                    AccountType.valueOf(
                            request.getAccountType()
                                    .toUpperCase()
                    );

            // Customer validation
            Customer customer =
                    customerRepository
                            .findByCustomerNumber(
                                    request.getCustomerNumber()
                            )
                            .orElseThrow(
                                    () -> new RuntimeException(
                                            "Customer not found"
                                    )
                            );

            // Generate unique account number
            long accountNumber =
                    System.currentTimeMillis();

            // Create account
            Account account = new Account();

            account.setAccountNumber(
                    accountNumber
            );

            account.setAccountBalance(
                    request.getAccountBalance()
            );

            account.setAccountType(
                    type
            );

            account.setAtmPin(
                    request.getAtmPin()
            );

            account.setCustomer(
                    customer
            );

            Account savedAccount =
                    accountRepository.save(
                            account
                    );

            // Response
            AccountResponseDto response =
                    new AccountResponseDto(
                            savedAccount.getAccountNumber(),
                            savedAccount.getAccountBalance(),
                            savedAccount.getAccountType()
                                    .name()
                    );

            return ResponseEntity.ok(
                    response
            );

        } catch(Exception e){

            e.printStackTrace();

            return ResponseEntity
                    .status(500)
                    .build();
        }
    }
}