package model;

import java.util.Random;

public class RandomGenerator {

    private static RandomGenerator randomGenerator;
    private final Random random;

    private RandomGenerator() {
        this.random = new Random();
    }

    public static RandomGenerator getInstance() {
        if (randomGenerator == null) {
            randomGenerator = new RandomGenerator();
        }
        return randomGenerator;
    }

    public int gerRandomInteger(int bound) {
        return random.nextInt(bound);
    }
}
