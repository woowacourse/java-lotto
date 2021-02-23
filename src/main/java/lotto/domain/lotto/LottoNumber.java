package lotto.domain.lotto;

import java.util.Objects;

public class LottoNumber {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    public static final String OUT_RANGE_NUMBER_ERROR_MESSAGE = "범위가 초과된 숫자 입력입니다.";
    private final int number;

    public LottoNumber(final int number) {
        validateLottoRange(number);
        this.number = number;
    }

    private void validateLottoRange(final int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(OUT_RANGE_NUMBER_ERROR_MESSAGE);
        }
    }

    public int getNumber() {
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
