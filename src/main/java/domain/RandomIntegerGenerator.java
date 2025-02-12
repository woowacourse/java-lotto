package domain;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerGenerator implements IntegerGenerator {
    public int generateIntegerInRange(int startInclusive, int endInclusive) {
        Random random = ThreadLocalRandom.current();
        return random.nextInt(startInclusive, endInclusive + 1);
    }
}
