package com.springboot.Intelligent.Banking.Platform.Dto.AccountDto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AccountRequestDto {

    @Min(value=1000 , message = "Minimum Balance 1000rs required")
    private double accountBalance;
    private Long accountNumber;
    private Long customerNumber;
    @NotBlank
    private String accountType;
    @NotBlank
    @Length(min = 4, max = 4, message = "ATM PIN must be 4 digits")
    private Integer atmPin;
}
