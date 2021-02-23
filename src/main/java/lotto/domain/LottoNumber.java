package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private final int number;

    public LottoNumber(int value) {
        validateRange(value);
        this.number = value;
    }

    public int getNumber() {
        return this.number;
    }

    private void validateRange(int value) {
        if (value < MINIMUM || MAXIMUM < value) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d 이상, %d 이하여야 합니다.", MINIMUM, MAXIMUM));
        }
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
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }
}
