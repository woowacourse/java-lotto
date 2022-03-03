package lotto.domain;

import java.util.Objects;

public class Money {

    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    public Money minus(Money other) {
        return Money.from(this.amount - other.amount);
    }

    public Money divide(Money other) {
        if (other.amount == 0) {
            throw new IllegalArgumentException("나누는 금액은 0이 될 수 없습니다.");
        }
        return Money.from(this.amount / other.amount);
    }

    public boolean isGreatThanOrEqualTo(Money other) {
        return this.amount >= other.amount;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Money money = (Money)o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
