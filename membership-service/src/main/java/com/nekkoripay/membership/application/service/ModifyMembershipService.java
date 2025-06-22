package com.nekkoripay.membership.application.service;

import com.nekkoripay.common.UseCase;
import com.nekkoripay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.nekkoripay.membership.adapter.out.persistence.MembershipMapper;
import com.nekkoripay.membership.application.port.in.ModifyMembershipCommand;
import com.nekkoripay.membership.application.port.in.ModifyMembershipUseCase;
import com.nekkoripay.membership.application.port.out.ModifyMembershipPort;
import com.nekkoripay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp()),
                new Membership.MembershipRefreshToken("")
        );

        // entity -> Membership
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
