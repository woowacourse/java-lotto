package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Lotto number out of range.";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < MINIMUM_NUMBER || lottoNumber > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE);
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