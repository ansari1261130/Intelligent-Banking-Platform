package com.springboot.Intelligent.Banking.Platform.Controllers;

import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerDetailsDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerResponseDto;
import com.springboot.Intelligent.Banking.Platform.Services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banking")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @RequestBody CustomerRequestDto customerRequestDto
    ) {
        return customerService.createCustomer(customerRequestDto);
    }

    @GetMapping("/customer/{customerNumber}")
    public ResponseEntity<CustomerDetailsDto> getCustomer(@PathVariable Long customerNumber) {
        return customerService.getCustomerDetails(customerNumber);
    }
}