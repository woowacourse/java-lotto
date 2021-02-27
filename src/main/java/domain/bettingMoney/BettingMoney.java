package domain.bettingMoney;

import domain.lotto.TicketCount;

import java.math.BigDecimal;

public class BettingMoney {
    private static final String NOT_POSITIVE_MONEY_EXCEPTION_MESSAGE = "양수의 금액만 가능합니다. 현재 입력 금액: %d";
    private static final String NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE = "%d 이상의 금액만 가능합니다. 현재 입력 금액: %d";

    private final int bettingMoney;

    public BettingMoney(final int bettingMoney) {
        validatePositiveBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public TicketCount getTicketCount(int ticketPrice) {
        validateEnoughBettingMoney(this.bettingMoney, ticketPrice);
        return new TicketCount(this.bettingMoney / ticketPrice);
    }

    public BigDecimal getEarningRate(final long prize) {
        BigDecimal prizeMoney = BigDecimal.valueOf(prize);
        BigDecimal bettingMoney = BigDecimal.valueOf(this.bettingMoney);
        return prizeMoney.divide(bettingMoney, 2, BigDecimal.ROUND_HALF_UP);
    }

    private void validatePositiveBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException(String.format(NOT_POSITIVE_MONEY_EXCEPTION_MESSAGE, bettingMoney));
        }
    }

    private void validateEnoughBettingMoney(final int bettingMoney, final int ticketPrice) {
        if (bettingMoney < ticketPrice) {
            throw new IllegalArgumentException(String.format(NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE, ticketPrice, bettingMoney));
        }
    }
}
