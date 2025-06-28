package com.nekkoripay.membership.adapter.out.persistence;

import com.nekkoripay.common.PersistenceAdapter;
import com.nekkoripay.membership.application.port.out.FindMembershipPort;
import com.nekkoripay.membership.application.port.out.ModifyMembershipPort;
import com.nekkoripay.membership.application.port.out.RegisterMembershipPort;
import com.nekkoripay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
//    private final VaultAdapter vaultAdapter;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        // email 만 암호화 해보는 것으로 결정.
//        String encryptedEmail = vaultAdapter.encrypt(membershipEmail.getEmailValue());
        MembershipJpaEntity jpaEntity = new MembershipJpaEntity(
                membershipName.getNameValue(),
                membershipAddress.getAddressValue(),
                membershipEmail.getEmailValue(),
                membershipIsValid.isValidValue(),
                membershipIsCorp.isCorpValue(),
                ""
        );
        membershipRepository.save(jpaEntity);

        MembershipJpaEntity clone = jpaEntity.clone();
        clone.setEmail(membershipEmail.getEmailValue());

        return clone;
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        MembershipJpaEntity membershipJpaEntity = membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
        String encryptedEmailString = membershipJpaEntity.getEmail();
//        String decryptedEmailString = vaultAdapter.decrypt(encryptedEmailString);

        MembershipJpaEntity clone = membershipJpaEntity.clone();
        clone.setEmail(membershipJpaEntity.getEmail());
        return clone;
    }

    @Override
    public List<MembershipJpaEntity> findMembershipListByAddress(Membership.MembershipAddress membershipAddress) {
        // 관악구, 서초구, 강남구 중 하나
        String address = membershipAddress.getAddressValue();
        List<MembershipJpaEntity> membershipJpaEntityList = membershipRepository.findByAddress(address);
        List<MembershipJpaEntity> cloneList = new ArrayList<>();

        for (MembershipJpaEntity membershipJpaEntity : membershipJpaEntityList) {
            String encryptedEmailString = membershipJpaEntity.getEmail();
//            String decryptedEmailString = vaultAdapter.decrypt(encryptedEmailString);

            MembershipJpaEntity clone = membershipJpaEntity.clone();
            clone.setEmail(membershipJpaEntity.getEmail());
            cloneList.add(clone);
        }
        return cloneList;
    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp, Membership.MembershipRefreshToken membershipRefreshToken) {
        MembershipJpaEntity entity = membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));

        // email 만 암호화 해보는 것으로 결정.
//        String encryptedEmail = vaultAdapter.encrypt(membershipEmail.getEmailValue());

        entity.setName(membershipName.getNameValue());
        entity.setAddress(membershipAddress.getAddressValue());
        entity.setEmail(membershipEmail.getEmailValue());
        entity.setCorp(membershipIsCorp.isCorpValue());
        entity.setValid(membershipIsValid.isValidValue());
        entity.setRefreshToken(membershipRefreshToken.getRefreshToken());
        membershipRepository.save(entity);

        // Todo 리턴 전에 새로운 객체로 평문화된 멤버 정보를 리턴해 줘야 해요.
        MembershipJpaEntity clone = entity.clone();
        clone.setEmail(membershipEmail.getEmailValue());
        return clone;
    }
}
