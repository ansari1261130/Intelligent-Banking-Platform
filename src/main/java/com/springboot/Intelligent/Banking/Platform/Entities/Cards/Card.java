package com.springboot.Intelligent.Banking.Platform.Entities.Cards;

import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Customer;
import com.springboot.Intelligent.Banking.Platform.Entities.Transaction.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long cardId;
    @Column(unique = true, nullable = false)
    @Length(min = 16, max = 16)
    private Long cardNumber;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(nullable = false)
    private String cardHolderName;
    @Column(nullable = false)
    private Date cardExpiryDate;
    @Column(nullable = false)
    @Length (min = 3, max = 3)
    private int cardCVV;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
