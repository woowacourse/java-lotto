package lotto;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoMachine {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static Lotto issue() {
        return new Lotto(generateNumbers());
    }

    private static Set<Integer> generateNumbers() {
        Set<Integer> uniqueNumbers = new TreeSet<>();
        while (uniqueNumbers.size() < LOTTO_NUMBER_COUNT) {
            uniqueNumbers.add(getRandomNumberInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
        return Collections.unmodifiableSet(uniqueNumbers);
    }

    private static int getRandomNumberInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}
