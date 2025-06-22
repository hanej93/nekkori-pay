package com.nekkoripay.membership.application.port.in;


import com.nekkoripay.membership.domain.JwtToken;
import com.nekkoripay.membership.domain.Membership;

public interface AuthMembershipUseCase {

    JwtToken loginMembership(LoginMembershipCommand command);

    JwtToken refreshJwtTokenByRefreshToken(RefreshTokenCommand command);

    boolean validateJwtToken(ValidateTokenCommand command);

    Membership getMembershipByJwtToken(ValidateTokenCommand command);
}
