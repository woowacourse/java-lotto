package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY_UNIT = 1000;

    private final int money;

    public Money(int money) {
        validateMoneyLimit(money);
        this.money = money;
    }

    private void validateMoneyLimit(final int money) {
        if (money < MIN_MONEY_UNIT) {
            throw new IllegalArgumentException("입력 금액은 1000원 이상이어야 합니다.");
        }
    }

    private int getChange() {
        return money % MIN_MONEY_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
