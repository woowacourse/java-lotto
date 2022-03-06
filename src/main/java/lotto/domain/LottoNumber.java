package lotto.domain;

import static lotto.constant.ErrorMessage.ERROR_LOTTO_NUMBER_WRONG_RANGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;
    private static final Map<Integer, LottoNumber> CACHE;

    static {
        CACHE = IntStream.rangeClosed(LOWER_BOUND, UPPER_BOUND)
                .boxed()
                .collect(Collectors.toUnmodifiableMap(Function.identity(), LottoNumber::new));
    }

    private final int value;

    private LottoNumber(final int number) {
        validateRange(number);
        this.value = number;
    }

    public static LottoNumber valueOf(int number) {
        validateRange(number);
        return CACHE.get(number);
    }

    public static List<LottoNumber> values() {
        return new ArrayList<>(CACHE.values());
    }

    private static void validateRange(final int number) {
        if (isValidRange(number)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_WRONG_RANGE.message());
        }
    }

    private static boolean isValidRange(final int number) {
        return number < LOWER_BOUND || number > UPPER_BOUND;
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
