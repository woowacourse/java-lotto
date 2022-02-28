package lotto.model;

import lotto.model.exception.InvalidNumberRangeException;
import java.util.Objects;

public class LottoNumber {

    static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    static final int LOTTO_NUMBER_UPPER_BOUND = 45;

    private final int number;

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidNumberRangeException();
        }
        this.number = number;
    }

    private boolean isInvalidRange(int number) {
        return LOTTO_NUMBER_LOWER_BOUND > number || number > LOTTO_NUMBER_UPPER_BOUND;
    }

    public int getIntValue() {
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
