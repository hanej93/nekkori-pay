package com.nekkoripay.money.application.port.in;


import com.nekkoripay.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {

    void createMemberMoney(
            MemberMoney.MembershipId memberId,
            MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    );
}
