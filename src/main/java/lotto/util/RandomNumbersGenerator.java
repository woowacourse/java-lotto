package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumbersGenerator {
    private static final int START = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    private RandomNumbersGenerator() {
        numbers = new ArrayList<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            numbers.add(i);
        }
    }

    public static RandomNumbersGenerator getInstance() {
        return new RandomNumbersGenerator();
    }

    public List<Integer> generate() {
        List<Integer> semiNumbers = new ArrayList<>(numbers);
        Collections.shuffle(semiNumbers);
        return semiNumbers.subList(START, LOTTO_SIZE);
    }
}
