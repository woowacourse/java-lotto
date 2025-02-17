package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_MONEY_NOT_VALID;

public record Profit(double rate, boolean isProfit) {

    public static Profit from(long winningPrize, Money spentMoney) {
        if (spentMoney.amount() <= 0) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_VALID);
        }
        double rate = (double) winningPrize / spentMoney.amount();
        boolean isProfit = rate >= 1.0;

        return new Profit(rate, isProfit);
    }
}
