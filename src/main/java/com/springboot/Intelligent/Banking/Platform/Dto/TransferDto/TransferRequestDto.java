package com.springboot.Intelligent.Banking.Platform.Dto.TransferDto;

import lombok.Data;

@Data
public class TransferRequestDto {

    private Long sourceAccountNumber;
    private Long destinationAccountNumber;
    private double amount;
}
