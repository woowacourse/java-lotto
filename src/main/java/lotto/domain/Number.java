package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;
    private static final String ERROR_MESSAGE = MIN_VALUE + "부터 " + MAX_VALUE + "의 숫자여야 합니다.";
    private static final List<Number> cache;

    static {
        cache = IntStream.rangeClosed(1, 45)
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }

    private final int value;

    private Number(final int value) {
        validateValueRange(value);
        this.value = value;
    }

    public static Number getInstance(int value) {
        validateValueRange(value);
        return cache.get(value - 1);
    }

    private static void validateValueRange(final int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Number number = (Number) object;
        return value == number.value;
    }
}
