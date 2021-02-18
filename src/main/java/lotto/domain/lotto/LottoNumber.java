package lotto.domain.lotto;

import static lotto.utils.Config.END;
import static lotto.utils.Config.START;

import java.util.Objects;

import lotto.utils.Validation;
import lotto.view.ErrorMessages;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int value;

    public LottoNumber(String number) {
        if (!Validation.isNumeric(number)) {
            throw new IllegalArgumentException();
        }

        int lottoNumber = Integer.parseInt(number);
        validateLottoNumberRange(lottoNumber);
        this.value = lottoNumber;
    }

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.value = lottoNumber;
    }

    private void validateLottoNumberRange(int lottoNumber) {
        if (lottoNumber < START || lottoNumber > END) {
            throw new IllegalArgumentException(
                    ErrorMessages.ERROR_LOTTO_NUMBER_OUT_OF_BOUND.getMessage());
        }
    }

    public int getValue() {
        return value;
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
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return getValue() - o.getValue();
    }

}
