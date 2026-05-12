package com.springboot.Intelligent.Banking.Platform.Repositories;

import com.springboot.Intelligent.Banking.Platform.Entities.Cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(Long cardNumber);
}
