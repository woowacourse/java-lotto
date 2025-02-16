package global.utils;

import java.util.Random;

public final class RandomNumber {

    private RandomNumber() {
    }

    public static int generateRandomNumber(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(1, 45);
    }
}
