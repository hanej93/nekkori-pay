package com.nekkoripay.payment.application.port.out;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
