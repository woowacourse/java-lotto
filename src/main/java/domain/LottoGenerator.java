package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int COUNT_OF_NUMBERS = 6;
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
