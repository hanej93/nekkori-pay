package com.nekkoripay.membership.application.port.in;

import com.nekkoripay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipUseCase {
	Membership findMembership(FindMembershipCommand command);
    List<Membership> findMembershipListByAddress(FindMembershipListByAddressCommand command);
}
