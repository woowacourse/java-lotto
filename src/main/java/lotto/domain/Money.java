package lotto.domain;

import lotto.validator.MoneyValidator;

public class Money {

    private long money;

    private Money(long money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public static Money generateMoneyByString(String money) {
        return new Money(Long.parseLong(money));
    }

    public void minusPrice(long price) {
        money -= price;
    }

    public int calculateTotalLottoCount(long lottoPrice) {
        return (int) (money / lottoPrice);
    }

    public double getProfitRate(long totalPrize) {
        return (double) totalPrize / (double) money;
    }

    public String toStringProfitRateUntilSecondDecimal(long totalPrize) {
        return String.valueOf(Math.floor(getProfitRate(totalPrize) * 100) / 100.0);
    }
}
