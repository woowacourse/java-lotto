package domain;

import java.util.Objects;

public class Money {
    private final int money;

    private Money(int money) {
        validateMoneyNotNegative(money);
        this.money = money;
    }

    private void validateMoneyNotNegative(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("돈은 0원보다 작을 수 없습니다.");
        }
    }

    public static Money from(int money) {
        return new Money(money);
    }

    public boolean lessThan(Money m) {
        return money < m.money;
    }

    public Money minus(Money m) {
        return Money.from(money - m.money);
    }

    public Money times(int n) {
        return Money.from(n * money);
    }

    public int toInt() {
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
