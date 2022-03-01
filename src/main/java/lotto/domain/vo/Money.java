package lotto.domain.vo;

import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(0L);
    public static final Money LOTTO_PRICE = new Money(1000L);

    private static final int MINIMUM_MONEY = 0;

    private final long amount;

    public Money(long amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public Money minus(int amount) {
        return new Money(this.amount - amount);
    }

    public Money multiply(int amount) {
        return new Money(this.amount * amount);
    }

    public boolean isGreaterThan(Money money) {
        return this.amount > money.amount;
    }

    public boolean hasRemainder(int amount) {
        return this.amount % amount > ZERO.amount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money1 = (Money)o;
        return amount == money1.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    private void validatePositive(long money) {
        if (money < MINIMUM_MONEY) {
            throw new IllegalArgumentException("돈은 0이상이어야 한다.");
        }
    }
}
