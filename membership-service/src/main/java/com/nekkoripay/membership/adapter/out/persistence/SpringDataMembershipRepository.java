package com.nekkoripay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
}
