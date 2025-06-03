package com.nekkoripay.membership.adapter.out.persistence;

import com.nekkoripay.common.PersistenceAdapter;
import com.nekkoripay.membership.application.port.out.FindMembershipPort;
import com.nekkoripay.membership.application.port.out.ModifyMembershipPort;
import com.nekkoripay.membership.application.port.out.RegisterMembershipPort;
import com.nekkoripay.membership.domain.Membership;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

	private final SpringDataMembershipRepository membershipRepository;

	@Override
	public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
		Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress,
		Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
		return membershipRepository.save(
			new MembershipJpaEntity(
				membershipName.getNameValue(),
				membershipEmail.getEmailValue(),
				membershipAddress.getAddressValue(),
				membershipIsValid.isValidValue(),
				membershipIsCorp.isCorpValue()
			)
		);
	}

	@Override
	public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
		return membershipRepository.getReferenceById(Long.parseLong(membershipId.getMembershipId()));
	}

	@Override
	public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
		MembershipJpaEntity entity = membershipRepository.getReferenceById(Long.parseLong(membershipId.getMembershipId()));
		entity.setName(membershipName.getNameValue());
		entity.setAddress(membershipAddress.getAddressValue());
		entity.setEmail(membershipEmail.getEmailValue());
		entity.setCorp(membershipIsCorp.isCorpValue());
		entity.setValid(membershipIsValid.isValidValue());

		return membershipRepository.save(entity);
	}
}
