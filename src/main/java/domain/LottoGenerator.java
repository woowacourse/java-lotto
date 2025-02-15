package domain;

import static domain.properties.LottoProperties.COUNT_OF_NUMBERS;
import static domain.properties.LottoProperties.MAX_NUMBER;
import static domain.properties.LottoProperties.MIN_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {
    private static final Random random = new Random();

    public static List<Integer> generate() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < COUNT_OF_NUMBERS) {
            int i = random.nextInt(MAX_NUMBER) + MIN_NUMBER;
            numbers.add(i);
        }
        return new ArrayList<>(numbers);
    }
}
