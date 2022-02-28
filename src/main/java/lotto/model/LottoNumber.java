package lotto.model;

import java.util.Objects;

public class LottoNumber {
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;
    private static final String ERROR_OUT_OF_RANGE_BONUS_NUMBER = "볼 번호가 1~45 범위 내에 해당하지 않습니다.";

    private final int number;

    LottoNumber(int number) {
        validateRangeLottoNumber(number);
        this.number = number;
    }

    private void validateRangeLottoNumber(Integer number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_BONUS_NUMBER);
        }
    }

    Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
