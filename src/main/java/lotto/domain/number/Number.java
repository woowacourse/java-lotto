package lotto.domain.number;

import java.util.Objects;

public class Number {

    private int value;

    public Number(int value) {
        this.value = value;
    }

    public Number(String input) {
        validateNumeric(input);
        this.value = Integer.parseInt(input);
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
