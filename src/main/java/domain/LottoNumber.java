package domain;

import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MIN_LOTTO_NUMBER;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber of(int value) {
        validateRange(value);
        return new LottoNumber(value);
    }

    private static void validateRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorCode.WINNING_NUMBER_NOT_IN_RANGE.getMessage());
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LottoNumber that = (LottoNumber) obj;
        return value == that.getValue();
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(value, o.value);
    }
}
