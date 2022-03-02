package lotto.domain;

import lotto.exception.MoneyException;
import lotto.validator.MoneyValidator;

public class Money {

    private long money;
    private long spentMoney;

    private Money(long money) {
        MoneyValidator.validate(money);
        this.money = money;
        this.spentMoney = 0;
    }

    public static Money generateMoneyByString(String money) {
        return new Money(Long.parseLong(money));
    }

    public void spendMoney(long moneyToSpend) {
        if (money < spentMoney + moneyToSpend) {
            throw new MoneyException(MoneyException.MONEY_SPENT_LIMIT_ERROR_MESSAGE);
        }
        spentMoney += moneyToSpend;
    }

    public int calculateCountCanBuy() {
        return (int) ((money - spentMoney) / Lotto.LOTTO_PRICE);
    }

    private double getProfitRate(long totalPrize) {
        return (double) totalPrize / (double) spentMoney;
    }

    public String toStringProfitRateUntilSecondDecimal(long totalPrize) {
        return String.valueOf(Math.floor(getProfitRate(totalPrize) * 100) / 100.0);
    }
}
