package lotto.domain.number;

import java.util.Objects;

public class Number {

    private final int value;

    public Number(int value) {
        this(String.valueOf(value));
    }

    public Number(String input) {
        validateNumeric(input);
        this.value = Integer.parseInt(input);
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 숫자가 아니거나 범위를 벗어났습니다.");
        }
    }

    public int getValueAsInt() {
        return value;
    }

    @Override
    public Number clone() {
        return new Number(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number number = (Number) o;
        return value == number.value;
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public Number add(Number number) {
        return new Number(value + number.value);
    }

    public Number subtract(Number number) {
        return new Number(value - number.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
