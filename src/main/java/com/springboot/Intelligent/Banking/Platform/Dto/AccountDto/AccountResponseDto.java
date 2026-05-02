package com.springboot.Intelligent.Banking.Platform.Dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseDto {

    private Long accountNumber;
    private double accountBalance;
    private String accountType;
}