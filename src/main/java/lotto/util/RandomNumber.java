package lotto.util;

import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;

import java.util.Random;

public class RandomNumber implements RandomUtil {

    private static final Random random = new Random();

    @Override
    public int generate(int min, int max) {
        return random.nextInt(LOTTO_NUMBER_MAX_RANGE) + LOTTO_NUMBER_MIN_RANGE;
    }
}
