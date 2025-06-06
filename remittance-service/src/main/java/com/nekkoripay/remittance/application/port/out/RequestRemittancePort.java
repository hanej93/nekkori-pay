package com.nekkoripay.remittance.application.port.out;

import com.nekkoripay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.nekkoripay.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
