package com.nekkoripay.banking.adapter.in.web;

import com.nekkoripay.banking.application.port.in.RegisterBankAccountCommand;
import com.nekkoripay.banking.application.port.in.RegisterBankAccountUseCase;
import com.nekkoripay.banking.domain.RegisteredBankAccount;
import com.nekkoripay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registeredBankAccountUseCase;

    @PostMapping(path = "/banking/account/register")
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();

        return registeredBankAccountUseCase.registerBankAccount(command);
    }
}
