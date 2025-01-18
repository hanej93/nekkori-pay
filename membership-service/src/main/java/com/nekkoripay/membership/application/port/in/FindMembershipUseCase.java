package com.nekkoripay.membership.application.port.in;

import com.nekkoripay.membership.domain.Membership;

public interface FindMembershipUseCase {
	Membership findMembership(FindMembershipCommand command);
}
