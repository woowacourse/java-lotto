package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String ERROR_NEGATIVE_INPUT_MESSAGE = "금액은 음수를 입력하면 안됩니다.";

    private final int amount;

    public Money(int amount) {
        validateNegative(amount);
        this.amount = amount;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INPUT_MESSAGE);
        }
    }

    public int canBuyNumber(Money itemMoney) {
        return this.amount / itemMoney.amount;
    }

    public int getAmount() {
        return amount;
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
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
