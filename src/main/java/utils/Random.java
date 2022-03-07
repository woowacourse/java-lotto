package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_UNIT_TO_CORRECT = 1;

    public static int lottoNumber() {
        return ThreadLocalRandom.current()
                .nextInt(LOTTO_NUMBER_MAX) + LOTTO_NUMBER_UNIT_TO_CORRECT;
    }
}
