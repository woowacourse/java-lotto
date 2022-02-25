package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호의 범위는 1 ~ 45 사이입니다.";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateNumberRange(int winningNumber) {
        if (winningNumber < START_NUMBER || winningNumber > END_NUMBER) {
            throw new IllegalArgumentException(WINNING_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public LottoNumber getLottoNumber() {
        return new LottoNumber(lottoNumber);
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
