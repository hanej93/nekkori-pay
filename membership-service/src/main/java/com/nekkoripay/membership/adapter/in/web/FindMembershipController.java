package com.nekkoripay.membership.adapter.in.web;

import com.nekkoripay.common.WebAdapter;
import com.nekkoripay.membership.application.port.in.FindMembershipCommand;
import com.nekkoripay.membership.application.port.in.FindMembershipListByAddressCommand;
import com.nekkoripay.membership.application.port.in.FindMembershipUseCase;
import com.nekkoripay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(
            @PathVariable(name = "membershipId") String membershipId
    ) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }

    @GetMapping(path = "/membership/address/{addressName}")
    ResponseEntity<List<Membership>> findMembershipListByAddress(@PathVariable String addressName) {
        FindMembershipListByAddressCommand command = FindMembershipListByAddressCommand.builder()
                .addressName(addressName)
                .build();
        return ResponseEntity.ok(findMembershipUseCase.findMembershipListByAddress(command));
    }
}
