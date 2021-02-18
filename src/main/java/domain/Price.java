package domain;

import java.util.Objects;

public class Price {
    private static final int ZERO = 0;
    private static final int MIN_PRICE = 1000;
    private static final int MAX_PRICE = 1_000_000;
    private static final String OUT_OF_LIMIT_ERROR = "범위를 벗어났습니다.";
    private static final String NOT_INT_ERROR = "정수가 아닙니다.";
    private static final String NULL_ERROR = "null 이 입력되었습니다.";
    private static final String NOT_POSITIVE_INT_ERROR = "양의 정수가 아닙니다.";
    private static final String NOT_DIVISIBLE_ERROR = "1000원 단위 금액이 아닙니다.";

    private final int value;

    private Price(final int value) {
        this.value = value;
    }

    private Price(final String value) {
        this.value = Integer.parseInt(value);
    }

    public static Price valueOf(final String value) {
        validate(value);
        return new Price(value);
    }

    private static void validate(final String value) {
        validateNotNull(value);
        validateNumeric(value);

        final int number = Integer.parseInt(value);
        validatePositiveInteger(number);
        validateRange(number);
        validateDivisible(number);
    }

    private static void validateRange(final int value) {
        if (value < MIN_PRICE || value > MAX_PRICE) {
            throw new IllegalArgumentException(OUT_OF_LIMIT_ERROR);
        }
    }

    private static void validateNumeric(final String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INT_ERROR);
        }
    }

    private static void validateNotNull(final String value) {
        if (value == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
    }

    private static void validatePositiveInteger(final int value) {
        if (value <= ZERO) {
            throw new IllegalArgumentException(NOT_POSITIVE_INT_ERROR);
        }
    }

    private static void validateDivisible(final int value) {
        if (value % MIN_PRICE != ZERO) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_ERROR);
        }
    }

    public int getValue() {
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
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
