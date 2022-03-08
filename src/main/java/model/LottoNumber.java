package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS = new HashMap<>();

    static {
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            LOTTO_NUMBERS.put(i, new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    private LottoNumber(int number) {
        checkRange(number);
        this.lottoNumber = number;
    }

    private static void checkRange(int number) {
        if (isInvalidRange(number)) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    private static boolean isInvalidRange(int number) {
        return MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER;
    }

    public static LottoNumber of(int number) {
        checkRange(number);
        return LOTTO_NUMBERS.get(number);
    }

    public int intValue() {
        return lottoNumber;
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
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
