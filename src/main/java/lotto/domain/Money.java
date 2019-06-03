package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY = 0;
    private final int money;

    public Money(int money) {
        if (money < MIN_MONEY) {
            throw new InvalidMoneyException("돈은 적어도 " + MIN_MONEY + "이상이어야 합니다.");
        }
        this.money = money;
    }

    public Money add(Money another) {
        return new Money(money + another.money);
    }

    public int getValue() {
        return money;
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
