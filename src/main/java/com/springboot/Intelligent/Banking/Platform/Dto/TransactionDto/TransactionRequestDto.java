package com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto;

import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDto {
    private Long accountNumber;
    private double amount;
}
