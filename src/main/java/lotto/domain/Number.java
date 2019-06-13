package lotto.domain;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number implements Comparable<Number> {
    private static final String ERROR_MESSAGE = "숫자의 범위는 1-45입니다.";
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int number;

    private static Map<Integer, Number> cacheMap;

    static {
        cacheMap = IntStream.rangeClosed(MIN, MAX)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Number::new));
/*
.groupBy() <- 같은 key 값을 가지고 있는 Collection으로 묶는 함수 (e.g. Map<Key, Collection<Value>)
.toMap() <- 단순한 Map
 */
    }

    private Number(int number) {
        this.number = number;
        validOverBound();
    }

    public static Number of(int number) {
        return cacheMap.get(number);
    }

    private void validOverBound() {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    @Override
    public int compareTo(Number differentNumber) {
        if (this.number < differentNumber.number) {
            return -1;
        }
        if (this.number > differentNumber.number) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
