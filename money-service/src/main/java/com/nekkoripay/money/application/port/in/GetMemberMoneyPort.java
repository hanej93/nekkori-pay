package com.nekkoripay.money.application.port.in;


import com.nekkoripay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.nekkoripay.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId memberId
    );
}
