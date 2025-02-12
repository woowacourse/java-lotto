package lotto;

import static lotto.LottoNumberConstants.LOTTO_NUMBER_COUNT;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoMachine {

    public static Lotto issue() {
        return new Lotto(generateNumbers());
    }

    private static Set<Integer> generateNumbers() {
        Set<Integer> uniqueNumbers = new TreeSet<>();
        while (uniqueNumbers.size() < LOTTO_NUMBER_COUNT.value()) {
            uniqueNumbers.add(getRandomNumberInRange(LOTTO_NUMBER_MIN.value(), LOTTO_NUMBER_MAX.value()));
        }
        return Set.copyOf(uniqueNumbers);
    }

    private static int getRandomNumberInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}
