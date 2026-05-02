package com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountResponseDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDetailsDto {

    private Long customerNumber;
    private String customerName;
    private String customerEmail;
    private Long customerPhoneNumber;
    private String customerAddress;
    private Gender customerGender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerDOB;

    private List<AccountResponseDto> accounts;
}