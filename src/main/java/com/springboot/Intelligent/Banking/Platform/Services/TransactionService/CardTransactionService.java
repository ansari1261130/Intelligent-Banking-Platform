package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class CardTransactionService {

    private final CardRepository cardRepository;

    public CompletableFuture<TransactionDto> execute(CardRequestDto request) {

        Long cardNumber = request.getCardNumber();
        if (cardNumber == null) {
            throw new RuntimeException("Card number is required");
        }
        return CompletableFuture.completedFuture(null);
    }
}
