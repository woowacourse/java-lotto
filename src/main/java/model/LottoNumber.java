package model;

import exception.InvalidRangeLottoNumberException;
import java.util.Objects;

public class LottoNumber {
    private final int lottoNumber;

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
        this.lottoNumber = number;
    }

    private boolean isInvalidRange(int number) {
        return 1 > number || number > 45;
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
