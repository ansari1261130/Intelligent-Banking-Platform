package com.springboot.Intelligent.Banking.Platform.Dto.CardDto;


import com.springboot.Intelligent.Banking.Platform.Entities.Cards.CardStatus;
import com.springboot.Intelligent.Banking.Platform.Entities.Cards.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CardResponseDto {
    private Long cardNumber;
    private String cardHolderName;
    private CardType cardType;
    private CardStatus cardStatus;
    private Date cardExpiryDate;
    private int cardCVV;
}
