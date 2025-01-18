package com.nekkoripay.membership.application.port.out;

import com.nekkoripay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.nekkoripay.membership.domain.Membership;

public interface RegisterMembershipPort {

	MembershipJpaEntity createMembership(
		Membership.MembershipName membershipName,
		Membership.MembershipEmail membershipEmail,
		Membership.MembershipAddress membershipAddress,
		Membership.MembershipIsValid membershipIsValid,
		Membership.MembershipIsCorp membershipIsCorp
	);
}
