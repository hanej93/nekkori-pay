package com.nekkoripay.banking.adapter.axon.aggregate;

import com.nekkoripay.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.nekkoripay.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.nekkoripay.banking.adapter.axon.event.FirmbankingRequestCreatedEvent;
import com.nekkoripay.banking.adapter.axon.event.FirmbankingRequestUpdatedEvent;
import com.nekkoripay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.nekkoripay.banking.adapter.out.external.bank.FirmbankingResult;
import com.nekkoripay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.nekkoripay.banking.application.port.out.RequestFirmbankingPort;
import com.nekkoripay.banking.domain.FirmbankingRequest;
import com.nekkoripay.common.event.RequestFirmbankingCommand;
import com.nekkoripay.common.event.RequestFirmbankingFinishedEvent;
import com.nekkoripay.common.event.RollbackFirmbankingFinishedEvent;
import com.nekkoripay.common.event.RollbackFirmbankingRequestCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class FirmbankingRequestAggregate {

    @AggregateIdentifier
    private String id;

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
    private int firmbankingStatus;

    @CommandHandler
    public FirmbankingRequestAggregate (CreateFirmbankingRequestCommand command) {
        System.out.println("CreateFirmbankingRequestCommand Handler");
        apply(new FirmbankingRequestCreatedEvent(command.getFromBankName(), command.getFromBankAccountNumber(), command.getToBankName(), command.getToBankAccountNumber(), command.getMoneyAmount()));
    }

    @CommandHandler
    public FirmbankingRequestAggregate(RequestFirmbankingCommand command, RequestFirmbankingPort firmbankingPort, RequestExternalFirmbankingPort externalFirmbankingPort){
        System.out.println("FirmbankingRequestAggregate Handler");
        id = command.getAggregateIdentifier();

        // from -> to
        // 펌뱅킹 수행!
        firmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getToBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.ToBankName("nekkori-bank"),
                new FirmbankingRequest.ToBankAccountNumber("123-333-9999"),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier(id));

        // firmbanking!
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                    command.getFromBankName(),
                    command.getFromBankAccountNumber(),
                    command.getToBankName(),
                    command.getToBankAccountNumber(),
                    command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();

        // 0. 성공, 1. 실패
        apply(new RequestFirmbankingFinishedEvent(
                command.getRequestFirmbankingId(),
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                resultCode,
                id
        ));
    }

    @CommandHandler
    public FirmbankingRequestAggregate(@NotNull RollbackFirmbankingRequestCommand command, RequestFirmbankingPort firmbankingPort, RequestExternalFirmbankingPort externalFirmbankingPort) {
        System.out.println("RollbackFirmbankingRequestCommand Handler");
        id = UUID.randomUUID().toString();

        // rollback 수행 (-> 법인 계좌 -> 고객 계좌 펌뱅킹)
        firmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName("nekkori"),
                new FirmbankingRequest.FromBankAccountNumber("123-333-9999"),
                new FirmbankingRequest.ToBankName(command.getBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier(id));

        // firmbanking!
        FirmbankingResult result = externalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        "nekkori",
                        "123-333-9999",
                        command.getBankName(),
                        command.getBankAccountNumber(),
                        command.getMoneyAmount()
                ));

        int res = result.getResultCode();

        apply(new RollbackFirmbankingFinishedEvent(
                command.getRollbackFirmbankingId(),
                command.getMembershipId(),
                id)
        );
    }

    @CommandHandler
    public String handle(UpdateFirmbankingRequestCommand command) {
        System.out.println("UpdateFirmbankingRequestCommand Handler");

        id = command.getAggregateIdentifier();
        apply(new FirmbankingRequestUpdatedEvent(command.getFirmbankingStatus()));

        return id;
    }

    @EventSourcingHandler
    public void on (FirmbankingRequestCreatedEvent event) {
        System.out.println("FirmbankingRequestCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
    }

    @EventSourcingHandler
    public void on (FirmbankingRequestUpdatedEvent event) {
        System.out.println("FirmbankingRequestUpdatedEvent Sourcing Handler");
        firmbankingStatus = event.getFirmbankingStatus();
    }

    public FirmbankingRequestAggregate() {
    }
}
