package com.nekkoripay.membership.adapter.in.web;

import com.nekkoripay.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nekkoripay.membership.application.port.in.RegisterMembershipCommand;
import com.nekkoripay.membership.application.port.in.RegisterMembershipUseCase;
import com.nekkoripay.membership.domain.Membership;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

	private final RegisterMembershipUseCase registerMembershipUseCase;

	@PostMapping(path = "/membership/register")
	Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
		RegisterMembershipCommand command = RegisterMembershipCommand.builder()
			.name(request.getName())
			.address(request.getAddress())
			.email(request.getEmail())
			.isValid(true)
			.isCorp(request.isCorp())
			.build();

		return registerMembershipUseCase.registerMembership(command);
	}
}
