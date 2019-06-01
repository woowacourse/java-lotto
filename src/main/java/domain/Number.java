package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number implements Comparable<Number> {
    // [NUMBER_FROM, NUMBER_TO]
    public static final int NUMBER_FROM = 1;
    public static final int NUMBER_TO = 45;

    private final int number;

    private Number(int number) {
        this.number = number;
    }

    public static Number from(int number) {
        if (!NumberCache.contains(number)) {
            throw new IllegalArgumentException(
                    String.format("가능한 로또 번호가 아닙니다. 가능한 범위 [%d, %d]",
                            NUMBER_FROM, NUMBER_TO));
        }
        return NumberCache.get(number);
    }

    public static List<Number> generateAllNumbers() {
        return IntStream.rangeClosed(NUMBER_FROM, NUMBER_TO)
                .mapToObj(i -> Number.from(i))
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Number b) {
        return Integer.compare(number, b.number);
    }

    private static class NumberCache {
        static final Number[] cache;

        static {
            cache = new Number[NUMBER_TO - NUMBER_FROM + 1];

            for (int i = NUMBER_FROM; i <= NUMBER_TO; i++) {
                set(i, new Number(i));
            }
        }

        private static void set(int i, Number number) {
            cache[i - NUMBER_FROM] = number;
        }

        private static Number get(int i) {
            return cache[i - NUMBER_FROM];
        }

        static boolean contains(int i) {
            return NUMBER_FROM <= i && i <= NUMBER_TO;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
