package model;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY = 0;

    private final int amount;

    public Money(int amount) {
        if (amount < MIN_MONEY) {
            throw new IllegalArgumentException("잘못된 입력입니다. 0 이상의 수로 다시 입력해주세요.");
        }
        this.amount = amount;
    }

    public Money(String input) {
        this(Integer.parseInt(input.trim()));
    }

    public int getLottoPurchaseAmount() {
        return amount / Lotto.PRICE;
    }

    public double getEarningRate(Money investment) {
        return ((double) amount / investment.getAmount() - 1.0) * 100.0;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
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
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}