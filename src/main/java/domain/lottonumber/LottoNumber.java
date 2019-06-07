package domain.lottonumber;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    static final int MIN_NUMBER = 1;
    static final int MAX_NUMBER = 45;

    private final int number;

    private LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalLottoNumberException();
        }
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
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

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public boolean isOf(int number) {
        return this.number == number;
    }
}
