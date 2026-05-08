package com.springboot.Intelligent.Banking.Platform.Services.CardService;

import com.springboot.Intelligent.Banking.Platform.Entities.Account.Account;
import org.springframework.stereotype.Service;

@Service
public class CardValidationService {

    public boolean validatePin(
            Account account,
            Integer enteredPin
    ) {

        if(enteredPin == null){
            return false;
        }

        return account.getAtmPin()
                .equals(enteredPin);
    }
}