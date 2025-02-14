package lotto.domain;

import static lotto.exception.ErrorMessage.OUT_OF_RANGE;

import java.util.Objects;
import lotto.exception.LottoException;

public class LottoNumber {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
        validateNumberRange();
    }

    private void validateNumberRange() {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new LottoException(OUT_OF_RANGE);
        }
    }

    public int getNumber() {
        return number;
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

}
