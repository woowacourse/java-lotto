package lotto.domain.number;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber createLottoNumber(int number) {
        return new LottoNumber(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
