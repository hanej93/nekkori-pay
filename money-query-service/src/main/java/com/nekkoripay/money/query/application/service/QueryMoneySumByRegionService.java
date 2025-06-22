package com.nekkoripay.money.query.application.service;

import com.nekkoripay.common.UseCase;
import com.nekkoripay.money.query.adapter.axon.QueryMoneySumByAddress;
import com.nekkoripay.money.query.application.port.in.QueryMoneySumByRegionQuery;
import com.nekkoripay.money.query.application.port.in.QueryMoneySumByRegionUseCase;
import com.nekkoripay.money.query.domain.MoneySumByRegion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;

import java.util.concurrent.ExecutionException;

@UseCase
@RequiredArgsConstructor
@Transactional
public class QueryMoneySumByRegionService implements QueryMoneySumByRegionUseCase {
    private final QueryGateway queryGateway;
    @Override
    public MoneySumByRegion queryMoneySumByRegion(QueryMoneySumByRegionQuery query) {
        try {
            return queryGateway.query(new QueryMoneySumByAddress(query.getAddress())
                    , MoneySumByRegion.class).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
