package lotto.domain;

import lotto.validator.MoneyValidator;

public class Money {

    private final long money;

    public Money(long money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public double calculateProfitRate(long totalWinningPrize) {
        return (double) totalWinningPrize / (double) money;
    }

    public long getMoney() {
        return money;
    }
}
