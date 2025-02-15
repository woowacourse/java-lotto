package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoMachine {

    public static Lotto issue() {
        return new Lotto(generateNumbers());
    }

    private static Set<Integer> generateNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < LOTTO_NUMBER_COUNT) {
            uniqueNumbers.add(getRandomNumberInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
        return Set.copyOf(uniqueNumbers);
    }

    private static int getRandomNumberInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}
