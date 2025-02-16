package lotto.utils;

import java.util.Random;

public class RandomNumberGenerator implements RandomNumberStrategy {
    private final Random random = new Random();

    @Override
    public int run(final int startNumber, final int endNumber) {
        return random.nextInt(endNumber - startNumber + 1) + startNumber;
    }
}
