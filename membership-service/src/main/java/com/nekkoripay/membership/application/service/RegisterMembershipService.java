package com.nekkoripay.membership.application.service;

import com.nekkoripay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.nekkoripay.membership.adapter.out.persistence.MembershipMapper;
import com.nekkoripay.membership.application.port.in.RegisterMembershipCommand;
import com.nekkoripay.membership.application.port.in.RegisterMembershipUseCase;
import com.nekkoripay.membership.application.port.out.RegisterMembershipPort;
import com.nekkoripay.membership.domain.Membership;

import common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

	private final RegisterMembershipPort registerMembershipPort;
	private final MembershipMapper membershipMapper;

	@Override
	public Membership registerMembership(RegisterMembershipCommand command) {
		MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
			new Membership.MembershipName(command.getName()),
			new Membership.MembershipEmail(command.getEmail()),
			new Membership.MembershipAddress(command.getAddress()),
			new Membership.MembershipIsValid(command.isValid()),
			new Membership.MembershipIsCorp(command.isCorp())
		);
		// entity -> Membership
		return membershipMapper.mapToDomainEntity(jpaEntity);
	}
}
