package domain.lotto;

import java.util.Objects;

public class LottoNumber implements Comparable {

    private int number;

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException();
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
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
    public int compareTo(Object o) {
        if (o == null || !(o instanceof LottoNumber)) {
            return 1;
        }
        LottoNumber targetNumber = (LottoNumber)o;
        return this.number - targetNumber.number;
    }
}
