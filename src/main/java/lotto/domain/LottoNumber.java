package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;
    private static final String LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE = "로또 숫자의 정상 범위를 벗어난 숫자입니다.";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < LOWER_BOUND || lottoNumber > UPPER_BOUND) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
