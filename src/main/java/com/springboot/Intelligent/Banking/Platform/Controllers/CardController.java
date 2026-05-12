package com.springboot.Intelligent.Banking.Platform.Controllers;

import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardResponseDto;
import com.springboot.Intelligent.Banking.Platform.Services.CardService.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/banking")
public class CardController {

    private final CardService cardService;

    @PostMapping("/createCard")
    public ResponseEntity<String> cardService (
            @RequestBody CardRequestDto request
    ) {
        return cardService.createCard(request);
    }

    @GetMapping("/getCardDetails")
    public ResponseEntity<CardResponseDto> getCardDetails(
            @RequestBody Long cardNumber
    ) {
        return cardService.getCardDetails(cardNumber);
    }
}
