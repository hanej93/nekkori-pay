package com.nekkoripay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyChangingRequest {

    private String targetMembershipId;

    // 무조건 증액 요청 (충전)

    private int amount;
}
