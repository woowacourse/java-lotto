package lotto.util;

import java.util.Random;

public class LottoNumberGenerator {

    private static final Random random = new Random();

    public int generate(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
