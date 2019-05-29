package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RandomNumbersGenerator {
    private static final int START = 0;

    private final List<Integer> numbers;
    private final int count;

    private RandomNumbersGenerator(final int min, final int max, final int size) {
        numbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        this.count = size;
    }

    static RandomNumbersGenerator of(final int min, final int max, final int size) {
        return new RandomNumbersGenerator(min, max, size);
    }

    List<Integer> generate() {
        List<Integer> semiNumbers = new ArrayList<>(numbers);
        Collections.shuffle(semiNumbers);
        return semiNumbers.subList(START, count);
    }
}
