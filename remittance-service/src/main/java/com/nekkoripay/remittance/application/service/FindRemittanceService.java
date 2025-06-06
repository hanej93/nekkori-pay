package com.nekkoripay.remittance.application.service;

import com.nekkoripay.common.UseCase;
import com.nekkoripay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.nekkoripay.remittance.application.port.in.FindRemittanceCommand;
import com.nekkoripay.remittance.application.port.in.FindRemittanceUseCase;
import com.nekkoripay.remittance.application.port.out.FindRemittancePort;
import com.nekkoripay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        //
        findRemittancePort.findRemittanceHistory(command);
        return null;
    }
}
