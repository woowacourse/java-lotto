package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String SHORT_MONEY_MESSAGE = "1000원 이상 입력해주세요.";
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(final int money) {
        validateBudgetMoney(money);
        this.money = money;
    }

    public int lottoCount() {
        return money / LOTTO_PRICE;
    }

    private void validateBudgetMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(SHORT_MONEY_MESSAGE);
        }
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
