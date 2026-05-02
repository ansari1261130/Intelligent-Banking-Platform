package com.springboot.Intelligent.Banking.Platform.Repositories;

import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(Long transactionId);
}
