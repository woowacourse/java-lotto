package domain;

import java.util.Objects;

public class LottoMoney {
    public static final int SINGLE_LOTTO_PRICE = 1000;
    private static final int MAX_PURCHASE_MONEY = 1_000_000;

    private final int value;

    public LottoMoney(final String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(final String value) {
        validateNotNull(value);
        validateNumeric(value);

        final int number = Integer.parseInt(value);
        validatePositiveInteger(number);
        validateRange(number);
        validateDivisible(number);
    }

    private void validateRange(final int value) {
        if (value < SINGLE_LOTTO_PRICE || value > MAX_PURCHASE_MONEY) {
            throw new IllegalArgumentException(
                    String.format("범위를 벗어났습니다: %d (범위: %d~%d)", value, SINGLE_LOTTO_PRICE, MAX_PURCHASE_MONEY));
        }
    }

    private void validateNumeric(final String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다: " + value);
        }
    }

    private void validateNotNull(final String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("null 이 입력되었습니다.");
        }
    }

    private void validatePositiveInteger(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("양의 정수가 아닙니다: " + value);
        }
    }

    private void validateDivisible(final int value) {
        if ((value % SINGLE_LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException(
                    String.format("%d원 단위의 금액이 아닙니다: %d원", SINGLE_LOTTO_PRICE, value));
        }
    }

    public double toScale(final long targetValue) {
        return (double) targetValue / value;
    }

    public int toTicketQuantity() {
        return value / SINGLE_LOTTO_PRICE;
    }

    public boolean isExchangeable(final int ticketQuantity) {
        return ticketQuantity <= toTicketQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoMoney lottoMoney = (LottoMoney) o;
        return value == lottoMoney.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
