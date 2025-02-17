package lotto.util;

import java.util.Random;

public class LottoNumberGenerator {

    private final Random random;

    public LottoNumberGenerator() {
        this.random = new Random();
    }

    public LottoNumberGenerator(long seed) {
        this.random = new Random(seed);
    }

    public int generate(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
