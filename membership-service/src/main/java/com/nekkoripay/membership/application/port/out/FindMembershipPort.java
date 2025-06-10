package com.nekkoripay.membership.application.port.out;

import com.nekkoripay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.nekkoripay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipPort {
	MembershipJpaEntity findMembership(
		Membership.MembershipId membershipId
	);

	List<MembershipJpaEntity> findMembershipListByAddress(
			Membership.MembershipAddress membershipAddress
	);
}
