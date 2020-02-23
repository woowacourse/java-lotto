package lotto.domain;

import lotto.domain.exception.OutOfRangeException;

import java.util.Objects;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final String OUT_OF_RANGE_LOTTO_NUMBER_EXCEPTION_MESSAGE = "Lotto number out of range.";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new OutOfRangeException(OUT_OF_RANGE_LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}