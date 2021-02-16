package lotto.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static int generateRandomNumber(int from, int to) {
        return random.nextInt(to) + from;
    }
}
