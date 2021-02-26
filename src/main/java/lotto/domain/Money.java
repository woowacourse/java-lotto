package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String SHORT_MONEY_MESSAGE = "1000원 이상 입력해주세요.";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "입력금액은 숫자여야합니다.";
    private static final String OUT_OF_BUDGET_ERROR_MESSAGE = "지불할 금액이 부족합니다.";
    private static final int LOTTO_PRICE = 1000;

    private int money;

    public Money(final String money) {
        Objects.requireNonNull(money);
        int integerMoney = parseInt(money);
        validateBudgetMoney(integerMoney);
        this.money = integerMoney;
    }

    public int lottoCount() {
        return this.money / LOTTO_PRICE;
    }

    public void deductMoney(int manualPurchase) {
        int purchaseTotal = manualPurchase * LOTTO_PRICE;
        if (purchaseTotal > money) {
            throw new IllegalArgumentException(OUT_OF_BUDGET_ERROR_MESSAGE);
        }
        money = money - purchaseTotal;
    }

    private int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private void validateBudgetMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(SHORT_MONEY_MESSAGE);
        }
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
