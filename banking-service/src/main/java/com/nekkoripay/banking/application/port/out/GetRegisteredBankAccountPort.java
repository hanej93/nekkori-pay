package com.nekkoripay.banking.application.port.out;


import com.nekkoripay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.nekkoripay.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
