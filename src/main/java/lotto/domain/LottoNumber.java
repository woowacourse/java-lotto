package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final String LOTTO_NUMBER_SCOPE_ERROR_MESSAGE = "범위(1 ~ 45)를 벗어난 로또 번호가 있습니다.";

    private int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateNumberScope(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    static void validateNumberScope(int lottoNumber) {
        if (lottoNumber > MAX_LOTTO_NUMBER || lottoNumber < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SCOPE_ERROR_MESSAGE);
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
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
