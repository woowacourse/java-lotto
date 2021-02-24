package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    public static final int MIN_NUMBER_VALUE = 1;
    public static final int MAX_NUMBER_VALUE = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    private final int value;

    static {
        IntStream.rangeClosed(0, MAX_NUMBER_VALUE)
                .forEach(i -> CACHE.add(new LottoNumber(i)));
    }

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int value) {
        if (value < MIN_NUMBER_VALUE || MAX_NUMBER_VALUE < value) {
            throw new IllegalArgumentException(
                    String.format("%d~%d의 범위에서 벗어났습니다: %d", MIN_NUMBER_VALUE, MAX_NUMBER_VALUE, value)
            );
        }
        return CACHE.get(value);
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
