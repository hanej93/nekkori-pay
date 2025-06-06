package com.nekkoripay.money.application.port.out;

import com.nekkoripay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.nekkoripay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.nekkoripay.money.domain.MemberMoney;
import com.nekkoripay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            int increaseMoneyAmount
    );
}
