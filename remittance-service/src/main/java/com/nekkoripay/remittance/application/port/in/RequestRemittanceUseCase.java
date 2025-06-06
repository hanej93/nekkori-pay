package com.nekkoripay.remittance.application.port.in;


import com.nekkoripay.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
