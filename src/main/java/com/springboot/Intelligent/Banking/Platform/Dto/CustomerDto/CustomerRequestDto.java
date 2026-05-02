package com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequestDto {

    @NotBlank
    private String customerName;
    @Email(message = "Please enter a valid email address")
    private String customerEmail;
    @NotBlank
    @Max(value = 10, message = "Phone number cannot exceed 10 digits")
    private Long customerPhoneNumber;
    private String customerAddress;
    private String customerGender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerDOB;
}
