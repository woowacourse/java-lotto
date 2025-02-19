package domain;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static exception.ExceptionMessage.LOTTO_RANGE_ERROR;

import exception.LottoException;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(int number) {
        validateLottoRange(number);
        this.number = number;
    }

    private void validateLottoRange(int number) {
        if (number < LOTTO_RANGE_MIN.getValue() || number > LOTTO_RANGE_MAX.getValue()) {
            throw LottoException.from(LOTTO_RANGE_ERROR);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottoNumber)) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) obj;
        return lottoNumber.number == this.number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    public int getNumber() {
        return number;
    }
}
