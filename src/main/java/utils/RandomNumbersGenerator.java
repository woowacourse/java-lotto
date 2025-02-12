package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> generate() {
        final List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 6) {
            final int number = (int) (Math.random() * 44) + 1;
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }
        Collections.sort(numbers);
        return numbers;
    }
}
