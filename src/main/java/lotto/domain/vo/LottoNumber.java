package lotto.domain.vo;

import lotto.constants.NumberLimit;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        NumberLimit.checkOutOfRange(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber number = (LottoNumber) o;
        return this.number == number.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
