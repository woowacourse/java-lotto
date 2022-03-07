package lotto.domain;

import lotto.exception.LottoPurchaseMoneyException;

public class LottoPurchaseMoney {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final int lottoPurchaseMoney;

    public LottoPurchaseMoney(int input) {
        checkUnit(input);
        this.lottoPurchaseMoney = input;
    }

    private void checkUnit(int input) {
        if (!isCorrectUnit(input)) {
            throw new LottoPurchaseMoneyException(LottoPurchaseMoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private boolean isCorrectUnit(int input) {
        return input % LOTTO_TICKET_PRICE == 0;
    }

    public int calculateTotalLottoCount() {
        return lottoPurchaseMoney / LOTTO_TICKET_PRICE;
    }

    public double calculateProfitRate(long totalPrize) {
        double profitRate = (double) totalPrize / (double) lottoPurchaseMoney;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
