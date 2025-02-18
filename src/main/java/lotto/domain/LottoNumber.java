package lotto.domain;

import static lotto.common.Constants.MAX_LOTTO_NUMBER;
import static lotto.common.Constants.MIN_LOTTO_NUMBER;
import static lotto.common.ErrorMessage.ONLY_LOTTO_NUMBER;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    private void validateNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ONLY_LOTTO_NUMBER.getMessage());
        }
    }

    public int toInteger() {
        return number;
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
