package domain;

import java.util.Objects;

public class Money {

    private static final int MINIMUM_AMOUNT = 10;
    private static final String MONEY_MUST_BE_DIVIDABLE_BY_TEN = "금액은 10 단위로 나누어 떨어져야 합니다.";
    public static final String MONEY_MUST_BE_OVER_ZERO = "금액은 마이너스가 될 수 없습니다.";
    public static final String NOT_SUFFICENT_MONEY = "해당 금액으로 구입할 수 없습니다";

    private final int amount;

    private Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public int getPurchasableNumber(int amount) {
        validateAmount(amount);
        return this.amount / amount;
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(MONEY_MUST_BE_OVER_ZERO);
        }

        if (amount % MINIMUM_AMOUNT != 0) {
            throw new IllegalArgumentException(MONEY_MUST_BE_DIVIDABLE_BY_TEN);
        }
    }

    public Money consume(Money other) {
        validateNotEnoughAmount(other);
        return Money.from(this.amount - other.amount);
    }

    private void validateNotEnoughAmount(Money other) {
        if (this.amount < other.amount) {
            throw new IllegalArgumentException(NOT_SUFFICENT_MONEY);
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
