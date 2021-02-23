package lotto.domain;

import java.util.Objects;
import lotto.domain.exception.InvalidLottoNumberException;

public class LottoNumber {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    public static void validate(int number) {
        if (number < MINIMUM || MAXIMUM < number) {
            throw new InvalidLottoNumberException();
        }
    }

    public int getNumber() {
        return this.number;
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
}
