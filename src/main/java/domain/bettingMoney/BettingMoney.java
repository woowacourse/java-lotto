package domain.bettingMoney;

import java.math.BigDecimal;

import static domain.lotto.LottoTicket.TICKET_PRICE;

public class BettingMoney {
    private static final String NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE = "%d 이상의 금액만 가능합니다. 현재 입력 금액: %d";

    private final int bettingMoney;

    public BettingMoney(final int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public int getTicketCount() {
        return this.bettingMoney / TICKET_PRICE;
    }

    public BigDecimal getEarningRate(final long prize) {
        BigDecimal prizeMoney = BigDecimal.valueOf(prize);
        BigDecimal bettingMoney = BigDecimal.valueOf(this.bettingMoney);
        return prizeMoney.divide(bettingMoney, 2, BigDecimal.ROUND_HALF_UP);
    }

    private void validateMoney(final int bettingMoney) {
        if (bettingMoney < TICKET_PRICE) {
            throw new IllegalArgumentException(String.format(NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE, TICKET_PRICE, bettingMoney));
        }
    }
}
