package com.nekkoripay.remittance.application.port.out;

import com.nekkoripay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.nekkoripay.remittance.application.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {

    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
