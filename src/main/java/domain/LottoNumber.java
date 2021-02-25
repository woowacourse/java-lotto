package domain;

import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_VALUE = 1;
    private static final int MAX_NUMBER_VALUE = 45;

    private final int value;

    public LottoNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < MIN_NUMBER_VALUE || value > MAX_NUMBER_VALUE) {
            throw new IllegalArgumentException("1에서 45 범위에서 벗어났습니다.");
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
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public int compareTo(LottoNumber o) {
        if (this.value - o.value > 0) {
            return 1;
        }
        if (this.value - o.value < 0) {
            return -1;
        }
        return 0;
    }
}
