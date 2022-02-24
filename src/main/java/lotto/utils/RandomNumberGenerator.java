package lotto.utils;

import java.util.Random;

import lotto.domain.LottoNumber;

public class RandomNumberGenerator {
    private static final Random random;
    public static final int MAX_NUMBER = 45;

    static {
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }
    public static LottoNumber generate() {
        return new LottoNumber(random.nextInt(MAX_NUMBER) + 1);
    }
}
