package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final int LOTTO_LOWER_BOUND = 1;
    public static final int LOTTO_UPPER_BOUND = 45;
    private final int lottoNumber;

    private LottoNumber(final int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber of(int lottoNumber) {
        checkValidNumber(lottoNumber);
        return new LottoNumber(lottoNumber);
    }

    public static void checkValidNumber(int lottoNumber) {
        if (lottoNumber < LOTTO_LOWER_BOUND || lottoNumber > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_LOWER_BOUND + "부터 " + LOTTO_UPPER_BOUND + "까지 가능합니다.");
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
