package com.nekkoripay.membership.adapter.in.web;

import com.nekkoripay.common.WebAdapter;
import com.nekkoripay.membership.application.port.in.AuthMembershipUseCase;
import com.nekkoripay.membership.application.port.in.LoginMembershipCommand;
import com.nekkoripay.membership.application.port.in.RefreshTokenCommand;
import com.nekkoripay.membership.application.port.in.ValidateTokenCommand;
import com.nekkoripay.membership.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class AuthMembershipController {

    private final AuthMembershipUseCase authMembershipUseCase;

    @PostMapping(path = "/membership/login")
    JwtToken loginMembership(@RequestBody LoginMembershipRequest request) {
        LoginMembershipCommand command = LoginMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .build();

        return authMembershipUseCase.loginMembership(command);
    }

    @PostMapping(path = "/membership/refresh-token")
    JwtToken refreshToken(@RequestBody RefreshTokenRequest request) {
        RefreshTokenCommand command = RefreshTokenCommand.builder()
                .refreshToken(request.getRefreshToken())
                .build();

        return authMembershipUseCase.refreshJwtTokenByRefreshToken(command);
    }

    @PostMapping(path = "/membership/token-validate")
    boolean validateToken(@RequestBody ValidateTokenRequest request) {
        ValidateTokenCommand command = ValidateTokenCommand.builder()
                .jwtToken(request.getJwtToken())
                .build();

        return authMembershipUseCase.validateJwtToken(command);
    }
}
