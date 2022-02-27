package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number implements Comparable<Number> {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final String ERROR_MESSAGE = MIN_VALUE + "부터 " + MAX_VALUE + "의 숫자여야 합니다.";
    private static final List<Number> cache;

    static {
        cache = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .mapToObj(Number::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private final int value;

    private Number(final int value) {
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

    public static List<Number> getRandomNumbers(int size) {
        List<Number> numbers = new ArrayList<>(cache);
        Collections.shuffle(numbers);
        return numbers.subList(0, size);
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Number o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        return "Number{" +
                "value=" + value +
                '}';
    }
}
