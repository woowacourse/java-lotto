package domain.ball;

import java.util.Objects;

public class LottoBall {
    private static final int MIN_LOTTO_VALUE = 1;
    private static final int MAX_LOTTO_VALUE = 45;
    public static final String PERMIT_LOTTO_NUMBER = "%d~%d 사이의 번호만 허용합니다.";

    private final int value;

    public LottoBall(final int value) {
        validateNumber(value);
        this.value = value;
    }

    private void validateNumber(final int value) {
        if (!isBetweenNumber(value)) {
            throw new IllegalArgumentException(String.format(PERMIT_LOTTO_NUMBER, MIN_LOTTO_VALUE, MAX_LOTTO_VALUE));
        }
    }

    private boolean isBetweenNumber(final int number) {
        return number >= MIN_LOTTO_VALUE && number <= MAX_LOTTO_VALUE;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall that = (LottoBall) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

