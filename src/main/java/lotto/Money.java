package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private static final int MINIMUM_MONEY = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long money;

    public Money(long money) {
        validatePositive(money);
        this.money = money;
    }

    public Money plus(Money money) {
        return new Money(this.money + money.money);
    }

    public Rate divide(Money money) {
        return new Rate(calculateRate(money));
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
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    private void validatePositive(long money) {
        if (money < MINIMUM_MONEY) {
            throw new IllegalArgumentException("돈은 0이상이어야 한다.");
        }
    }

    private BigDecimal calculateRate(Money money) {
        return BigDecimal.valueOf(this.money).divide(BigDecimal.valueOf(money.money), DECIMAL_PLACE, RoundingMode.DOWN);
    }
}
