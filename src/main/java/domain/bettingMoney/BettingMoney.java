package domain.bettingMoney;

import java.math.BigDecimal;

import static domain.lotto.LottoTicket.TICKET_PRICE;

public class BettingMoney {
    private static final String NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE = "%d 이상의 금액만 가능합니다. 현재 입력 금액: %d";

    private final int bettingMoney;

    private BettingMoney(final int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public static BettingMoney of(final int bettingMoney) {
        return new BettingMoney(bettingMoney);
    }

    public int getTicketCount() {
        return this.bettingMoney / TICKET_PRICE;
    }

    public BigDecimal getEarningRate(final int prizeMoney) {
        BigDecimal bettingMoney = BigDecimal.valueOf(this.bettingMoney);
        BigDecimal prize = BigDecimal.valueOf(prizeMoney);
        return prize.divide(bettingMoney);
    }

    private void validateMoney(final int bettingMoney) {
        if (bettingMoney < TICKET_PRICE) {
            throw new IllegalArgumentException(String.format(NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE, TICKET_PRICE, bettingMoney));
        }
    }
}
