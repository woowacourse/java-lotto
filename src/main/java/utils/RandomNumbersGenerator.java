package utils;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> generate() {
        final List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < Lotto.SIZE) {
            addUniqueRandomNumber(numbers);
        }
        Collections.sort(numbers);
        return numbers;
    }

    private void addUniqueRandomNumber(final List<Integer> numbers) {
        final int number = (int) (Math.random() * (Lotto.MAX_NUMBER - 1)) + Lotto.MIN_NUMBER;
        if (!numbers.contains(number)) {
            numbers.add(number);
        }
    }
}
