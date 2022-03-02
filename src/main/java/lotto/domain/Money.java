package lotto.domain;

import lotto.validator.MoneyValidator;

public class Money {

    private long money;
    private long moneySpent;

    private Money(long money) {
        MoneyValidator.validate(money);
        this.money = money;
        this.moneySpent = 0;
    }

    public static Money generateMoneyByString(String money) {
        return new Money(Long.parseLong(money));
    }

    public void spendMoney(long money) {
        moneySpent += money;
    }

    public int calculateTotalLottoCount(long lottoPrice) {
        return (int) (money / lottoPrice);
    }

    private double getProfitRate(long totalPrize) {
        return (double) totalPrize / (double) moneySpent;
    }

    public String toStringProfitRateUntilSecondDecimal(long totalPrize) {
        return String.valueOf(Math.floor(getProfitRate(totalPrize) * 100) / 100.0);
    }
}
