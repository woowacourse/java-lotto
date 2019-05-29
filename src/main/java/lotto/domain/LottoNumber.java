package lotto.domain;

import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoNumber {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    private static final String EX_RANGE_MESSAGE = "로또는 1 ~ 45 사이 값을 입력해야합니다.";
    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
        checkLottoNumberCondition();
    }

    private void checkLottoNumberCondition() {
        if (lottoNumber < MIN_RANGE || lottoNumber > MAX_RANGE) {
            throw new IllegalArgumentException(EX_RANGE_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
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
