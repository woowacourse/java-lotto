package lotto.domain;

import static lotto.common.Constants.MAX_LOTTO_NUMBER;
import static lotto.common.Constants.MIN_LOTTO_NUMBER;

import java.util.Objects;
import lotto.utils.NumberUtils;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(String number) {
        int parseNum = NumberUtils.parseInt(number);
        validateNumber(parseNum);
        this.number = parseNum;
    }

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoNumber from(String number) {
        return new LottoNumber(number);
    }

    private void validateNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("1~45사이의 숫자만 입력하세요.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public int compareTo(final LottoNumber o) {
        return this.number - o.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
