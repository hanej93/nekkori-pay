package com.nekkoripay.money.application.port.in;

import com.nekkoripay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMemberMoneyListByMembershipIdsCommand extends SelfValidating<FindMemberMoneyListByMembershipIdsCommand> {

    @NotNull
    private final List<String> membershipIds;

    public FindMemberMoneyListByMembershipIdsCommand(@NotNull List<String> targetMembershipId) {
        this.membershipIds = targetMembershipId;
        this.validateSelf();
    }
}
