package lotto.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import lotto.model.exception.NotEnoughMoneyException;

public class Money implements Comparable<Money> {

    static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money subtract(Money money) {
        if (isLessThan(money)) {
            throw new NotEnoughMoneyException();
        }
        return new Money(this.amount.subtract(money.amount));
    }

    private boolean isLessThan(Money money) {
        return this.compareTo(money) < 0;
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal divide(Money money) {
        return this.amount.divide(money.amount, 2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int compareTo(Money o) {
        return amount.compareTo(o.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "model.Money{" +
            "amount=" + amount +
            '}';
    }
}
