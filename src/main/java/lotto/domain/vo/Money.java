package lotto.domain.vo;

import java.util.Objects;

public class Money {

    private static final String ERROR_NEGATIVE_INPUT_MESSAGE = "금액은 음수를 입력하면 안됩니다.";

    private final int money;

    public Money(int money) {
        validateNegative(money);
        this.money = money;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INPUT_MESSAGE);
        }
    }

    public int canBuyNumber(Money amount) {
        return money / amount.money;
    }

    public int get() {
        return money;
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
