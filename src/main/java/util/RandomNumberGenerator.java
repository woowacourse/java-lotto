package util;

import static constant.LottoConstants.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomNumberGenerator {

    public static List<Integer> getRandomNumbers(final RandomGenerator numberGenerator) {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() != LOTTO_SIZE.getValue()) {
            int number = numberGenerator.generate();
            numbers.add(number);
        }
        return new ArrayList<>(numbers);
    }
}
