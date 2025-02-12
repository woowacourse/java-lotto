package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoRandomGenerator {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;

    public static List<Integer> generateNumbers() {
        Set<Integer> numbers = new HashSet<>();

        Random random = new Random();

        while(numbers.size() != LOTTO_NUMBER_SIZE) {
            numbers.add(random.nextInt(LOTTO_NUMBER_START, LOTTO_NUMBER_END));
        }

        return numbers.stream().toList();
    }
}
