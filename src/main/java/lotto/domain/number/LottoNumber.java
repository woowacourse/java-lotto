package lotto.domain.number;

import java.util.Objects;

public class LottoNumber {

    private static final int NUMBER_MINIMUM = 1;
    private static final int NUMBER_MAXIMUM = 45;

    private final Number number;

    public LottoNumber(Number number) {
        validateRange(number);
        this.number = number;
    }

    public LottoNumber(int number) {
        this(new Number(number));
    }

    public LottoNumber(String number) {
        this(new Number(number));
    }

    private void validateRange(Number number) {
        if (number.toInt() < NUMBER_MINIMUM || number.toInt() > NUMBER_MAXIMUM) {
            throw new IllegalArgumentException("범위 밖의 로또 번호 입니다.");
        }
    }

    public int toInt() {
        return number.toInt();
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
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number.toInt());
    }
}