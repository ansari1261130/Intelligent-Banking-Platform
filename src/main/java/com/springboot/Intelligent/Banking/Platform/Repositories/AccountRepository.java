package com.springboot.Intelligent.Banking.Platform.Repositories;

import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long accountNumber);
}
