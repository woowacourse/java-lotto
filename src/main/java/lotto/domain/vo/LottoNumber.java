package lotto.domain.vo;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_NUMBERS_RANGE_MESSAGE = "로또 숫자 범위는 1 ~ 45입니다.";

    private static final int LOTTO_MAXIMUM = 45;
    private static final int LOTTO_MINIMUM = 1;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);

        this.number = number;
    }

    private void validateRange(int number) {
        if (number < LOTTO_MINIMUM || LOTTO_MAXIMUM < number) {
            throw new IllegalArgumentException(ERROR_NUMBERS_RANGE_MESSAGE);
        }
    }

    public int get() {
        return number;
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
