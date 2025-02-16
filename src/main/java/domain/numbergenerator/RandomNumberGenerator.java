package domain.numbergenerator;

import static domain.lotto.Lotto.MAX_LOTTO_NUMBER;
import static domain.lotto.Lotto.MIN_LOTTO_NUMBER;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int generate() {
        Random random = new Random();
        return random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1);
    }
}
