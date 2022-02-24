package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money prize) {
        return new Money(this.amount.add(prize.amount));
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal divide(Money money) {
        return this.amount.divide(money.amount);
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
