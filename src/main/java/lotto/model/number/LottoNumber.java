package lotto.model.number;

import java.util.Objects;

public class LottoNumber {

    private static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 유효한 숫자가 아닙니다.";
    private static final int LOTTO_NUMBER_MINIMUM_RANGE = 1;
    private static final int LOTTO_NUMBER_MAXIMUM_RANGE = 45;

    private final int number;

    public LottoNumber(final int number) throws RuntimeException {
        validateRange(number);
        this.number = number;
    }

    protected void validateRange(final int number) throws RuntimeException {
        if (number < LOTTO_NUMBER_MINIMUM_RANGE || number > LOTTO_NUMBER_MAXIMUM_RANGE) {
            throw new RuntimeException(NUMBER_RANGE_ERROR_MESSAGE);
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
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
