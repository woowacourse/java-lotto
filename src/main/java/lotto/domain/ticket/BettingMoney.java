package lotto.domain.ticket;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public final class BettingMoney {
    private static final int MINIMUM_PRICE = 1000;
    private final int bettingMoney;

    private BettingMoney(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public static BettingMoney valueOf(int bettingMoney) {
        return new BettingMoney(bettingMoney);
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < MINIMUM_PRICE) {
            throw new IllegalArgumentException(String.format("%d는 최소 구매 금액보다 작습니다.", bettingMoney));
        }
    }

    public int getTicketCount() {
        return this.bettingMoney / LOTTO_PRICE;
    }

    public int getMoney() {
        return this.bettingMoney;
    }
}
