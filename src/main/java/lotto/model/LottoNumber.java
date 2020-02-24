package lotto.model;

import java.util.HashMap;
import java.util.Map;

import java.util.Objects;
import lotto.exception.OverRangeException;

public class LottoNumber {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private final static Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++ ) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new OverRangeException(Money.OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getNumber() {
        return number;
    }

    public static LottoNumber getLottoNumber(int number) {
        return lottoNumbers.get(number);
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
