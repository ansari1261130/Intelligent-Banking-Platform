package com.springboot.Intelligent.Banking.Platform.Services;
import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountResponseDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerDetailsDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CustomerDto.CustomerResponseDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Customer;
import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Gender;
import com.springboot.Intelligent.Banking.Platform.Repositories.AccountRepository;
import com.springboot.Intelligent.Banking.Platform.Repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(Long customerNumber) {
        try {
            Customer customer = customerRepository.findByCustomerNumber(customerNumber)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            List<AccountResponseDto> accountDtos = customer.getAccounts()
                    .stream()
                    .map(acc -> new AccountResponseDto(
                            acc.getAccountNumber(),
                            acc.getAccountBalance(),
                            acc.getAccountType().name()
                    ))
                    .toList();

            CustomerDetailsDto response = new CustomerDetailsDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerEmail(),
                    customer.getCustomerPhoneNumber(),
                    customer.getCustomerAddress(),
                    customer.getCustomerGender(),
                    customer.getCustomerDOB(),
                    accountDtos
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<CustomerResponseDto> createCustomer(CustomerRequestDto request) {
        try {
            Customer customer = new Customer();
            Long customerNumber = (long) (Math.random()*1000000000L);
            customer.setCustomerNumber(customerNumber);
            customer.setCustomerName(request.getCustomerName());
            customer.setCustomerEmail(request.getCustomerEmail());
            customer.setCustomerPhoneNumber(request.getCustomerPhoneNumber());
            customer.setCustomerAddress(request.getCustomerAddress());

            customer.setCustomerGender(
                    Gender.valueOf(request.getCustomerGender().toUpperCase())
            );

            customer.setCustomerDOB(request.getCustomerDOB());

            Customer savedCustomer = customerRepository.save(customer);

            CustomerResponseDto response = new CustomerResponseDto(
                    savedCustomer.getCustomerNumber(),
                    savedCustomer.getCustomerName(),
                    savedCustomer.getCustomerEmail(),
                    savedCustomer.getCustomerPhoneNumber(),
                    savedCustomer.getCustomerAddress(),
                    savedCustomer.getCustomerGender(),
                    savedCustomer.getCustomerDOB()
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
