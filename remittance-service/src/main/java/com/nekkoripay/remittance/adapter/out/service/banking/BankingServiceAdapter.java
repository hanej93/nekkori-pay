package com.nekkoripay.remittance.adapter.out.service.banking;

import com.nekkoripay.common.CommonHttpClient;
import com.nekkoripay.common.ExternalSystemAdapter;
import com.nekkoripay.remittance.application.port.out.banking.BankingInfo;
import com.nekkoripay.remittance.application.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;


    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {

        return null;
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }
}
