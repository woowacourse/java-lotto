package domain;

import java.util.Objects;

public class Money {
    private static final int ZERO = 0;
    private static final int MIN_PRICE = 1000;
    private static final int MAX_PRICE = 1_000_000;
    private static final String OUT_OF_LIMIT_ERROR = "구입 금액 범위를 벗어났습니다.";
    private static final String NOT_POSITIVE_INT_ERROR = "양의 정수가 아닙니다.";
    private static final String NOT_DIVISIBLE_ERROR = "1000원 단위 금액이 아닙니다.";

    private final int value;
    private final int numberOfTickets;

    private Money(final int value) {
        this.value = value;
        this.numberOfTickets = this.value / MIN_PRICE;
    }

    public static Money valueOf(final int value) {
        validate(value);
        return new Money(value);
    }

    private static void validate(final int value) {
        validatePositiveInteger(value);
        validateRange(value);
        validateDivisible(value);
    }

    private static void validateRange(final int value) {
        if (value < MIN_PRICE || value > MAX_PRICE) {
            throw new IllegalArgumentException(OUT_OF_LIMIT_ERROR);
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

    public boolean canBuy(int numberOfManualTickets) {
        return numberOfManualTickets >= 0 && numberOfManualTickets <= numberOfTickets;
    }

    public int numberOfAutoTicket(int numberOfManualTicket) {
        return numberOfTickets - numberOfManualTicket;
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
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
