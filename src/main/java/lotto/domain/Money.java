package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private static final int MINIMUM_MONEY = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long amount;

    public Money(long amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public Money plus(Money money) {
        return new Money(this.amount + money.amount);
    }

    public Money minus(Money money) {
        return new Money(this.amount - money.amount);
    }

    public BigDecimal divide(Money money) {
        return BigDecimal.valueOf(this.amount).divide(BigDecimal.valueOf(money.amount), DECIMAL_PLACE, RoundingMode.DOWN);
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

    public boolean isGreaterThan(Money money) {
        return this.amount >= money.amount;
    }
}
