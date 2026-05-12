package com.springboot.Intelligent.Banking.Platform.Services.CardService;

import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardResponseDto;
import com.springboot.Intelligent.Banking.Platform.Entities.Cards.Card;
import com.springboot.Intelligent.Banking.Platform.Repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    public ResponseEntity<String> createCard(CardRequestDto request) {
        try {
            Card card = new Card();
            card.setCardNumber(request.getCardNumber());
            card.setCardHolderName(request.getCardHolderName());
            card.setCardCVV(request.getCardCVV());
            card.setCardExpiryDate(request.getCardExpiryDate());
            card.setCardType(request.getCardType());
            card.setCardStatus(request.getCardStatus());
            cardRepository.save(card);
            return ResponseEntity.ok("Card created successfully");
         } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
         }
    }

    public ResponseEntity<CardResponseDto> getCardDetails(Long cardNumber) {
        try {
            Card card = cardRepository.findByCardNumber(cardNumber)
                    .orElseThrow(() -> new RuntimeException("Card not found"));



        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
