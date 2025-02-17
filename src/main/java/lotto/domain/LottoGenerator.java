package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {
    private static final Random random = new Random();

    public static List<Integer> generate() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.COUNT_OF_NUMBERS) {
            int i = random.nextInt(Lotto.MAX_NUMBER) + Lotto.MIN_NUMBER;
            numbers.add(i);
        }
        return new ArrayList<>(numbers);
    }
}
