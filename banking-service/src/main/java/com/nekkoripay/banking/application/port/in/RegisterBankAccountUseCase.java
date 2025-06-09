package com.nekkoripay.banking.application.port.in;


import com.nekkoripay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
    void registerBankAccountByEvent(RegisterBankAccountCommand command);
}
