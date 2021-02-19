package lotto.domain;

import lotto.exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private final int number;

    public LottoNumber(final int value) {
        validate(value);
        this.number = value;
    }

    private void validate(final int value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new InvalidLottoNumberException();
        }
    }

    public int getLottoNumber() {
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
