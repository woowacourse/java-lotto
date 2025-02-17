package utils;

import domain.Lotto;

import java.util.*;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> generate() {
        final Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.SIZE) {
            final int number = (int) (Math.random() * (Lotto.MAX_NUMBER - 1)) + Lotto.MIN_NUMBER;
            numbers.add(number);
        }
        return numbers.stream()
                .sorted()
                .toList();
    }
}
