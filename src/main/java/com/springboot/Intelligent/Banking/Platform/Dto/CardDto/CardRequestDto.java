package com.springboot.Intelligent.Banking.Platform.Dto.CardDto;

import com.springboot.Intelligent.Banking.Platform.Entities.Cards.CardStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Cards.CardType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class CardRequestDto {
    private Long cardNumber;
    private CardType cardType;
    private String cardHolderName;
    private Date cardExpiryDate;
    private int cardCVV;
    private CardStatus cardStatus;
}

