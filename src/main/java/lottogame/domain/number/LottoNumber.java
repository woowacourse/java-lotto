package lottogame.domain.number;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int FINISH_NUMBER = 45;

    private final int value;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_MAP = new HashMap<>();

    static {
        for (int number = START_NUMBER; number <= FINISH_NUMBER; ++number) {
            LOTTO_NUMBER_MAP.put(number, new LottoNumber(number));
        }
    }

    private LottoNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    public static LottoNumber of(final String value) {
        return LottoNumber.of(Integer.parseInt(value));
    }

    public static LottoNumber of(final int value) {
        validateRange(value);
        return LottoNumber.LOTTO_NUMBER_MAP.get(value);
    }

    private static void validateRange(final int value) {
        if (value < START_NUMBER || FINISH_NUMBER < value) {
            throw new IllegalArgumentException("로또 숫자 범위를 벗어났습니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
