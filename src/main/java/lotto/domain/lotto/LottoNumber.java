package lotto.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    public static final int MAX_LOTTO_LIMIT = 45;
    public static final int MIN_LOTTO_LIMIT = 1;
    public static final int NUM_LOTTO_LIMIT = 6;
    public static final String LOTTO_RANGE_ERROR_MESSAGE = "로또 범위 내의 값을 입력하여 주세요.";
    private final int value;
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_LIMIT, MAX_LOTTO_LIMIT)
            .forEach(i -> lottoNumbers.put(i, new LottoNumber(i)));
    }

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        validateLottoNumber(value);
        return lottoNumbers.get(value);
    }

    private static void validateLottoNumber(int value) {
        if (value < MIN_LOTTO_LIMIT || value > MAX_LOTTO_LIMIT) {
            throw new IllegalArgumentException(
                LOTTO_RANGE_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
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
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
