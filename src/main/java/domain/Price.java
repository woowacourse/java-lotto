package domain;

import java.util.Objects;

public class Price {

    private final int value;

    private Price(int value) {
        this.value = value;
    }

    private Price(String value) {
        this.value = Integer.parseInt(value);
    }

    public static Price valueOf(String value) {
        validate(value);
        return new Price(value);
    }

    private static void validate(String value) {
        validateNotNull(value);
        validateNumeric(value);

        int number = Integer.parseInt(value);
        validatePositiveInteger(number);
        validateRange(number);
        validateDivisible(number);
    }

    private static void validateRange(int value) {
        if (value < 1000 || value > 1000000) { //TODO const 포장
            throw new IllegalArgumentException("범위를 벗어났습니다.");
        }
    }

    private static void validateNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }

    private static void validateNotNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("null 이 입력되었습니다.");
        }
    }

    private static void validatePositiveInteger(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("양의 정수가 아닙니다.");
        }
    }

    private static void validateDivisible(int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위 금액이 아닙니다.");
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
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
