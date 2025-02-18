package domain;

import static domain.exception.ExceptionMessage.INVALID_RANGE;

import java.util.Objects;

public class LottoNumber {

    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    private final int lottoNumber;

    public static LottoNumber from(int lottoNumber) {
        validateRange(lottoNumber);

        return new LottoNumber(lottoNumber);
    }

    private static void validateRange(final int lottoNumber) {
        if (lottoNumber < LOTTO_MIN || lottoNumber > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
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
        return Objects.hashCode(lottoNumber);
    }
}
