package com.springboot.Intelligent.Banking.Platform.Controllers;

import com.springboot.Intelligent.Banking.Platform.Dto.CardDto.CardRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransferDto.TransferRequestDto;
import com.springboot.Intelligent.Banking.Platform.Services.TransactionService.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/banking")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/depositMoney")
    public ResponseEntity<TransactionDto> depositMoney(
            @RequestBody TransactionRequestDto request
    ) {
        return ResponseEntity.ok(
                transactionService.depositMoney(request)
        );
    }

    @PostMapping("/withdrawMoney")
    public CompletableFuture<ResponseEntity<TransactionDto>> withdrawMoney(
            @RequestBody TransactionRequestDto request
    ) {

        return transactionService
                .withdrawMoney(request)
                .thenApply(
                        ResponseEntity::ok
                );
    }

    @PostMapping("/transferMoney")
    public CompletableFuture<ResponseEntity<TransactionDto>> transferMoney(
            @RequestBody TransferRequestDto request
        ) {
            return transactionService
                    .transferMoney(request)
                    .thenApply(
                    ResponseEntity::ok
            );
    }

    public CompletableFuture<ResponseEntity<TransactionDto>> cardWithdrawMoney(
            @RequestBody CardRequestDto request
    ) {
        return transactionService
                .cardWithdrawMoney(request)
                .thenApply(
                        ResponseEntity::ok
                );
    }
}
