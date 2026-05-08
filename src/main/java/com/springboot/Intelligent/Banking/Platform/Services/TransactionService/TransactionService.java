package com.springboot.Intelligent.Banking.Platform.Services.TransactionService;

import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransactionDto.TransactionRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.TransferDto.TransferRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final DepositTransactionService depositService;
    private final WithdrawTransactionService withdrawService;
    private final TransferTransactionService transferService;


    public TransactionDto depositMoney(
            TransactionRequestDto request
    ) {
        return depositService.execute(request);
    }


    public CompletableFuture<TransactionDto> withdrawMoney(
            TransactionRequestDto request
    ) {
        return withdrawService.execute(request);
    }


    @Async
    public CompletableFuture<TransactionDto> transferMoney(
            TransferRequestDto request
    ) {
        return transferService.execute(request);
    }
}