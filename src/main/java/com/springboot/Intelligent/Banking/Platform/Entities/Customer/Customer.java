package com.springboot.Intelligent.Banking.Platform.Entities.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long customerId;

    @Column(unique = true, nullable = false)
    private Long customerNumber;

    private String customerName;
    @Column(unique = true, nullable = false)
    private String customerEmail;
    @Column(unique = true, nullable = false)
    private Long customerPhoneNumber;
    private String customerAddress;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerDOB;

    @Enumerated(EnumType.STRING)
    private Gender customerGender;

    // One customer can have multiple accounts
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Account> accounts;
}
