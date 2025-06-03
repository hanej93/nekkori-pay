package com.nekkoripay.banking.application.port.out;

import com.nekkoripay.banking.adapter.out.external.bank.BankAccount;
import com.nekkoripay.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request) ;
}
