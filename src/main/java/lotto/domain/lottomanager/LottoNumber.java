package lotto.domain.lottomanager;

import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final int LIMIT_MIN_NUM = 1;
    private static final int LIMIT_MAX_NUM = 45;
    private static final String ERROR_OUT_RANGE = "1부터 45사이의 수가 아닙니다.";

    private final int lottoNumber;

    public LottoNumber(int number) {
        checkValidLottoNumber(number);
        this.lottoNumber = number;
    }

    private void checkValidLottoNumber(int number) {
        checkOutRange(number);
    }

    private void checkOutRange(int number) {
        if (isOutRangeNumber(number)) {
            throw new IllegalArgumentException(ERROR_OUT_RANGE);
        }
    }

    private boolean isOutRangeNumber(int number) {
        return LIMIT_MIN_NUM > number || LIMIT_MAX_NUM < number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return Objects.equals(lottoNumber, that.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }

    @Override
    public int compareTo(Object o) {
        final LottoNumber that = (LottoNumber) o;
        return this.lottoNumber - that.lottoNumber;
    }
}
