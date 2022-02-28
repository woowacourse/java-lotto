package domain;

import java.util.Objects;

public class Money {

    private static final int MINIMUM_AMOUNT = 10;
    private static final String MONEY_MUST_BE_DIVIDABLE_BY_TEN = "금액은 10 단위로 나누어 떨어져야 합니다.";

    private final int amount;

    private Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public boolean isPurchasable(int amount) {
        return this.amount >= amount;
    }

    public int getPurchasableNumber(int amount) {
        return this.amount / amount;
    }

    public Money consume(int amount) {
        validateAmount(amount);
        return Money.from(this.amount - amount);
    }

    private void validateAmount(int amount) {
        if (amount % MINIMUM_AMOUNT != 0) {
            throw new IllegalArgumentException(MONEY_MUST_BE_DIVIDABLE_BY_TEN);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
