package lotto.domain.lottomanager;

import lotto.utils.NullCheckUtil;

import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final String ERROR_OUT_RANGE = "1부터 45사이의 수가 아닙니다.";

    private Integer lottoNumber;

    private LottoNumber(Integer number) {
        checkValidLottoNumber(number);
        this.lottoNumber = number;
    }

    private void checkValidLottoNumber(Integer number) {
        checkOutRange(number);
    }

    private void checkOutRange(Integer number) {
        if (isOutRangeNumber(number)) {
            throw new IllegalArgumentException(ERROR_OUT_RANGE);
        }
    }

    private boolean isOutRangeNumber(Integer number) {
        return LottoConstant.LIMIT_MIN_NUM > number || LottoConstant.LIMIT_MAX_NUM < number;
    }

    public static LottoNumber createLottoNumber(Integer number) {
        NullCheckUtil.checkNullInteger(number);
        return new LottoNumber(number);
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
        return this.lottoNumber.compareTo(that.lottoNumber);
    }
}
