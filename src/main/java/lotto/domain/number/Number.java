package lotto.domain.number;

import java.util.Objects;

public class Number {

    private final int value;

    private Number(int value) {
        this.value = value;
    }

    public static Number valueOf(int input) {
        return new Number(input);
    }

    public static Number valueOf(String input) {
        validateNumeric(input);
        return new Number(Integer.parseInt(input));
    }

    private static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 숫자가 아니거나 범위를 벗어났습니다.");
        }
    }

    public boolean isBiggerThan(int target) {
        return value > target;
    }

    public int toInt() {
        return value;
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
