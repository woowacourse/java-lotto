package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private static final int MINIMUM_MONEY = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long number;

    public Money(long number) {
        validatePositive(number);
        this.number = number;
    }

    private void validatePositive(long number) {
        if (number < MINIMUM_MONEY) {
            throw new IllegalArgumentException("돈은 0이상이어야 한다.");
        }
    }

    public Money plus(Money money) {
        return new Money(this.number + money.number);
    }

    public Money minus(Money money) {
        return new Money(this.number - money.number);
    }

    public BigDecimal divide(Money money) {
        return BigDecimal.valueOf(this.number).divide(BigDecimal.valueOf(money.number), DECIMAL_PLACE, RoundingMode.DOWN);
    }

    public boolean isGreaterThan(Money money) {
        return this.number >= money.number;
    }

    public long getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money1 = (Money) o;
        return number == money1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
