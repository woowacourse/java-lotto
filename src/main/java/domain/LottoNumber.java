package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    protected static final int NUMBER_MIN = 1;
    protected static final int NUMBER_MAX = 45;

    private final int value;

    public LottoNumber(int value) {
        validateLottoNumber(value);
        this.value = value;
    }

    private void validateLottoNumber(int value) {
        if (value < NUMBER_MIN || value > NUMBER_MAX) {
            throw new IllegalArgumentException("로또 숫자 범위 외 숫자입니다.");
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
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.value - o.value;
    }
}
