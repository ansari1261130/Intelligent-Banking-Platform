package com.springboot.Intelligent.Banking.Platform.Entities.Account;

import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Customer;
import jakarta.persistence.*;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(unique = true, nullable = false)
    private Long accountNumber;
    private double accountBalance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false, unique = true,length = 4)
    private Integer atmPin;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
