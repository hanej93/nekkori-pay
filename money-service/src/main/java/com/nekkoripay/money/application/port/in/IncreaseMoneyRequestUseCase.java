package com.nekkoripay.money.application.port.in;


import com.nekkoripay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);
    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);
    void increaseMoneyRequestByEvent(IncreaseMoneyRequestCommand command);
}
