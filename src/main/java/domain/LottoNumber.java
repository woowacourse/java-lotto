package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    public static final int MIN_NUMBER_VALUE = 1;
    public static final int MAX_NUMBER_VALUE = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int value;

    static {
        IntStream.rangeClosed(MIN_NUMBER_VALUE, MAX_NUMBER_VALUE)
                .forEach(i -> CACHE.put(i, new LottoNumber(i)));
    }

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int value) {
        return CACHE.computeIfAbsent(value, k -> {
            throw new IllegalArgumentException(
                    String.format("%d~%d의 범위에서 벗어났습니다: %d", MIN_NUMBER_VALUE, MAX_NUMBER_VALUE, value)
            );
        });
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
