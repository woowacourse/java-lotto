package domain;

import java.util.Objects;

class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int number;

    LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 숫자는 1 이상, 45 이하여야 합니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }
}
