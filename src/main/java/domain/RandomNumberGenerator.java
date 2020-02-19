package domain;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random random = new Random();
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;

    public static int generate() {
        return random.nextInt(MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
    }
}
