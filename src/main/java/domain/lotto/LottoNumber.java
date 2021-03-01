package domain.lotto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_VALUE = 1;
    private static final int MAX_NUMBER_VALUE = 45;

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int value;

    static {
        for (int number = MIN_NUMBER_VALUE; number <= MAX_NUMBER_VALUE; ++number) {
            CACHE.put(number, new LottoNumber(number));
        }
    }

    private LottoNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    public static LottoNumber of(final int value) {
        return CACHE.get(value);
    }

    public static List<LottoNumber> generateList(final int size) {
        return shuffledIntegerList().stream()
                .limit(size)
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<Integer> shuffledIntegerList() {
        final List<Integer> numbers = IntStream
                .rangeClosed(MIN_NUMBER_VALUE, MAX_NUMBER_VALUE)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers;
    }

    private void validateRange(final int value) {
        if (value < MIN_NUMBER_VALUE || value > MAX_NUMBER_VALUE) {
            throw new IllegalArgumentException(String.format("1에서 45 범위에서 벗어났습니다: %d", value));
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


    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.value - o.value, 0);
    }
}
