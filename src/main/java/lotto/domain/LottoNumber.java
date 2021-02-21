package lotto.domain;

import java.util.Objects;
import lotto.exception.InvalidLottoNumberException;

public class LottoNumber {

    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private final int number;

    public LottoNumber(final int number) {
        validate(number);
        this.number = number;
    }

    private void validate(final int number) {
        if (number < MIN_BOUND || number > MAX_BOUND) {
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
