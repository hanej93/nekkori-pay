package com.nekkoripay.money.query.application.port.in;

import com.nekkoripay.money.query.domain.MoneySumByRegion;

public interface QueryMoneySumByRegionUseCase {

    MoneySumByRegion queryMoneySumByRegion (QueryMoneySumByRegionQuery query);
}
