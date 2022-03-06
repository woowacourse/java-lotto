package lotto.domain;

import lotto.exception.MoneyException;

public class Money {

    private static final int UNIT_SIZE = 1000;

    private final int money;

    public Money(int input) {
        checkUnit(input);
        this.money = input;
    }

    private void checkUnit(int input) {
        if (!isCorrectUnit(input)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private boolean isCorrectUnit(int input) {
        return input % UNIT_SIZE == 0;
    }

    public int calculateTotalLottoCount(int lottoTicketPrice) {
        return money / lottoTicketPrice;
    }

    public double calculateProfitRate(long totalPrize) {
        double profitRate = (double) totalPrize / (double) money;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
