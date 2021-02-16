package domain;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_NUMBER_VALUE = 1;
    private static final int MAX_NUMBER_VALUE = 45;

    private final int value;

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int value) {
        validateRange(value);
        return new LottoNumber(value);
    }

    private static void validateRange(final int value) {
        if (value < MIN_NUMBER_VALUE || value > MAX_NUMBER_VALUE) {
            throw new IllegalArgumentException("1에서 45 범위에서 벗어났습니다.");
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
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
