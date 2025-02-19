package domain;

import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MIN_LOTTO_NUMBER;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public int generate() {
        return RANDOM.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1);
    }
}
