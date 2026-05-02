package com.springboot.Intelligent.Banking.Platform.Controllers;

import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountRequestDto;
import com.springboot.Intelligent.Banking.Platform.Dto.AccountDto.AccountResponseDto;
import com.springboot.Intelligent.Banking.Platform.Services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banking")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<AccountResponseDto> createAccount(
            @RequestBody AccountRequestDto accountRequestDto
            ) {
        return accountService.createAccount(accountRequestDto);
    }
}
