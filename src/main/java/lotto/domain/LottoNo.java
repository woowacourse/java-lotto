package lotto.domain;

import java.util.Objects;

public class LottoNo implements Comparable<LottoNo> {
    private static final String ERROR_MESSAGE_LOTTO_RANGE = "1이상 45이하의 숫자를 입력하세요.";
    private static final int MIN_LOTTO_NO = 1;
    private static final int MAX_LOTTO_NO = 45;

    private final int number;

    public LottoNo(int number) {
        validateLottoNo(number);
        this.number = number;
    }

    private void validateLottoNo(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NO || lottoNumber > MAX_LOTTO_NO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_RANGE);
        }
    }

    @Override
    public int compareTo(LottoNo other) {
        return this.number - other.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNo lottoNo = (LottoNo) o;
        return number == lottoNo.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number + "";
    }
}
