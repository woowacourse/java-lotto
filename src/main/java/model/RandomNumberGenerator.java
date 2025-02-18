package model;

import java.util.Random;

public class RandomNumberGenerator {

    private final Random random = new Random();

    public int generate(int maxRangeNumber) {
        return random.nextInt(maxRangeNumber) + 1;
    }
}
