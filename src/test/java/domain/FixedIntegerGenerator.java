package domain;

import java.util.List;

public class FixedIntegerGenerator implements IntegerGenerator {
    private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
    private int idx = 0;

    public int generateInteger(int startInclusive, int endInclusive) {
        return numbers.get(idx++);
    }
}
