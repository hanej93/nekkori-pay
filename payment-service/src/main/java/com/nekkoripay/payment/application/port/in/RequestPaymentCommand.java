package com.nekkoripay.payment.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaymentCommand {
    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
}
