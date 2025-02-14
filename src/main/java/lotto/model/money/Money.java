package lotto.model.money;

import java.util.Objects;

public class Money {

    private final int value;

    private Money(final int value) {
        validate(value);
        this.value = value;
    }

    public static Money from(final int value) {
        return new Money(value);
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
        }
    }

    public int divide(Money divisor) {
        validateDividable(divisor);
        return value / divisor.value;
    }

    private void validateDividable(final Money divisor) {
        if (value < divisor.value) {
            throw new IllegalArgumentException("금액이 " + divisor.value + "원 보다 작습니다.");
        }
        if (value % divisor.value != 0) {
            throw new IllegalArgumentException(divisor.value + "원 으로 정확히 나눌 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
