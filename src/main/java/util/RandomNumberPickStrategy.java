package util;

import java.util.Random;

public class RandomNumberPickStrategy implements NumberPickStrategy{
    private static final Random random = new Random();

    @Override
    public int pickInRange(int min, int max) {
        return random.nextInt(max) + min;
    }
}
