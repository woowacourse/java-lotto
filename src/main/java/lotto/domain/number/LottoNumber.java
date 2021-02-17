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

    private void validateRange(Number number) {
        if (number.getValue() < NUMBER_MINIMUM || number.getValue() > NUMBER_MAXIMUM) {
            throw new IllegalArgumentException("범위 밖의 로또 번호 입니다.");
        }
    }

    public Number getNumber() {
        return number.clone();
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
        return String.valueOf(number.getValue());
    }

}
