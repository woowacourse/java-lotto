package global.utils;

import java.util.Random;

public final class RandomNumber {

    private RandomNumber() {
    }

    public static int generateRandomNumber(final int origin, final int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }
}
