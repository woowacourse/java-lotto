package lotto.domain.lotto;

import java.util.Objects;

public class Number {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int value;

    public Number(final String text) {
        this(toInt(text));
    }

    public Number(int number) {
        validateValueRange(number);
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    private static int toInt(final String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자여야 합니다.");
        }
    }

    private void validateValueRange(final int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(MIN_VALUE + "부터 " + MAX_VALUE + "의 숫자여야 합니다.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Number number = (Number)object;
        return value == number.value;
    }
}
