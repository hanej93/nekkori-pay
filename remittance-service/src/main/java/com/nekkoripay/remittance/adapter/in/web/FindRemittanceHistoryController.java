package com.nekkoripay.remittance.adapter.in.web;

import com.nekkoripay.common.WebAdapter;
import com.nekkoripay.remittance.application.port.in.FindRemittanceCommand;
import com.nekkoripay.remittance.application.port.in.FindRemittanceUseCase;
import com.nekkoripay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindRemittanceHistoryController {

    private final FindRemittanceUseCase findRemittanceUseCase;

    @GetMapping( "/remittance/{membershipId}")
    List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return findRemittanceUseCase.findRemittanceHistory(command);
    }
}
