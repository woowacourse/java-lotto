package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigInteger.ZERO);

    private final BigInteger amount;

    public Money(int amount) {
        this.amount = BigInteger.valueOf(amount);
    }

    private Money(BigInteger amount) {
        this.amount = amount;
    }

    public Money add(Money prize) {
        return new Money(this.amount.add(prize.amount));
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigInteger.valueOf(factor)));
    }

    public BigDecimal divide(Money money) {
        return new BigDecimal(this.amount.divide(money.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money prize = (Money) o;
        return Objects.equals(amount, prize.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "model.Prize{" +
                "amount=" + amount +
                '}';
    }
}
