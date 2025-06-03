package com.nekkoripay.banking.application.port.out;

import com.nekkoripay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.nekkoripay.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
