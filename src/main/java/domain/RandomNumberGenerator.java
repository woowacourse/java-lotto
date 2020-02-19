package domain;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random random = new Random();

    public static int generate(final int maxLottoNumber, final int minLottoNumber) {
        return random.nextInt(maxLottoNumber) + minLottoNumber;
    }
}
