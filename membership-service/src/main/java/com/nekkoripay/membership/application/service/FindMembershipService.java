package com.nekkoripay.membership.application.service;

import com.nekkoripay.common.UseCase;
import com.nekkoripay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.nekkoripay.membership.adapter.out.persistence.MembershipMapper;
import com.nekkoripay.membership.application.port.in.FindMembershipCommand;
import com.nekkoripay.membership.application.port.in.FindMembershipListByAddressCommand;
import com.nekkoripay.membership.application.port.in.FindMembershipUseCase;
import com.nekkoripay.membership.application.port.out.FindMembershipPort;
import com.nekkoripay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(entity);
    }

    @Override
    public List<Membership> findMembershipListByAddress(FindMembershipListByAddressCommand command) {
        List<MembershipJpaEntity> membershipJpaEntities = findMembershipPort.findMembershipListByAddress(new Membership.MembershipAddress(command.getAddressName()));
        List<Membership> memberships = new ArrayList<>();

        for (MembershipJpaEntity membershipJpaEntity : membershipJpaEntities) {
            memberships.add(membershipMapper.mapToDomainEntity(membershipJpaEntity));
        }
        return memberships;
    }
}