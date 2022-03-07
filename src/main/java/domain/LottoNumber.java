package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_MESSAGE_NOT_IN_RANGE = "로또 번호는 1 ~ 45 사이의 숫자로 이루어져야 합니다.";

    private static final int MINIMUM_VALUE = 1;
    private static final int MAXIMUM_VALUE = 45;

    private static final HashMap<Integer, LottoNumber> LOTTO_NUMBERS = new HashMap<>();

    static {
        IntStream.rangeClosed(MINIMUM_VALUE, MAXIMUM_VALUE)
                .forEach(number -> LOTTO_NUMBERS.put(number, new LottoNumber(number)));
    }

    private final int value;

    private LottoNumber(int value) {
        validateInRange(value);
        this.value = value;
    }

    private void validateInRange(int value) {
        if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
        }
    }

    public static LottoNumber valueOf(final int number) {
        if (LOTTO_NUMBERS.containsKey(number)) {
            return LOTTO_NUMBERS.get(number);
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
    }

    public static List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(LOTTO_NUMBERS.values());
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.value - other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
