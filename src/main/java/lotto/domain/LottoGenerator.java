package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.Constants.MAX_LOTTO_NUMBER;
import static lotto.common.Constants.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoGenerator {

    RandomNumberStrategy randomNumberGenerator;

    public LottoGenerator(final RandomNumberStrategy randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public List<Integer> generateRandomNumbers() {
        final HashSet<Integer> randomNumbers = new HashSet<>();

        while (randomNumbers.size() < LOTTO_NUM_SIZE) {
            randomNumbers.add(randomNumberGenerator.run(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
        return new ArrayList<>(randomNumbers);
    }
}
