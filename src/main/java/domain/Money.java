package domain;

import java.util.Objects;

public class Money {
    private static final long MINIMUM_RANGE = 0;
    private static final String ERROR_NEGATIVE_NUMBER = "[ERROR] 양수만 입력해주세요.";

    private final long money;

    public Money(long money) {
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validatePositiveNumber(long input) {
        if (input < MINIMUM_RANGE) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_NUMBER);
        }
    }

    public long toLong() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
