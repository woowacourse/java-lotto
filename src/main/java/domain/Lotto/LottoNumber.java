package domain.Lotto;

import utils.ExceptionMessage;

import java.util.List;
import java.util.Objects;

import static utils.LottoNumberConstants.MAXIMUM_LOTTO_NUMBER;
import static utils.LottoNumberConstants.MINIMUM_LOTTO_NUMBER;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public int checkHit(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.contains(this)) {
            return 1;
        }
        return 0;
    }

    public int number() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}