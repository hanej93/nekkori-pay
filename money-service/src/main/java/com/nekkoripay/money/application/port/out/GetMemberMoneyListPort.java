package com.nekkoripay.money.application.port.out;


import com.nekkoripay.money.adapter.out.persistence.MemberMoneyJpaEntity;

import java.util.List;

public interface GetMemberMoneyListPort {
    List<MemberMoneyJpaEntity> getMemberMoneyPort(List<String> membershipIds);
}
