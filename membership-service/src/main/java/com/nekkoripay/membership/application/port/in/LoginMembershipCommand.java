package com.nekkoripay.membership.application.port.in;


import com.nekkoripay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public
class LoginMembershipCommand extends SelfValidating<LoginMembershipCommand> {
    private final String membershipId;

}
