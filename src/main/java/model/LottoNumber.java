package model;

import exception.InvalidRangeLottoNumberException;
import java.util.Objects;

public class LottoNumber {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final int lottoNumber;

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
        this.lottoNumber = number;
    }

    private boolean isInvalidRange(int number) {
        return MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER;
    }

    public int getIntValue() {
        return lottoNumber;
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
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
