package com.nekkoripay.banking.application.port.out;

import com.nekkoripay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.nekkoripay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingPort {

    FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus,
            FirmbankingRequest.FirmbankingAggregateIdentifier firmbankingAggregateIdentifier
    );

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(
            FirmbankingRequestJpaEntity entity
    );

    FirmbankingRequestJpaEntity getFirmbankingRequest(
            FirmbankingRequest.FirmbankingAggregateIdentifier firmbankingAggregateIdentifier
    );
}
