package domain;

import java.util.List;

public class FixedIntegerGenerator implements IntegerGenerator {
    private final List<Integer> numbers;
    private int idx = 0;

    public FixedIntegerGenerator() {
        this.numbers = List.of(1, 2, 3, 4, 5, 6);
    }

    public FixedIntegerGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int generateInteger(int startInclusive, int endInclusive) {
        int number = numbers.get(idx % numbers.size());
        idx++;
        return number;
    }
}
