package model;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY = 0;

    private final int amount;

    public Money(int amount) {
        if (amount < MIN_MONEY) {
            throw new IllegalArgumentException();
        }
        this.amount = amount;
    }

    public Money(String input) {
        this(Integer.parseInt(input.trim()));
    }

    public int lottoPurchaseAmount() {
        return this.amount / Lotto.PRICE;
    }

    public double earningRate(Money investment) {
        return ((double) this.amount / investment.amount() - 1.0) * 100.0;
    }

    public int amount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return String.valueOf(this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return this.amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.amount);
    }
}