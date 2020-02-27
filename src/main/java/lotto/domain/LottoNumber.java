package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private static final int MAX = 45;
    private static final int MIN = 1;
    private int value;

    public LottoNumber(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException("유효하지 않은 로또 번호입니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
