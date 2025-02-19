package util;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator{

    @Override
    public int generate() {
        Random random = new Random();
        return random.nextInt(LOTTO_RANGE_MAX.getValue() - LOTTO_RANGE_MIN.getValue() + 1)
                + LOTTO_RANGE_MIN.getValue();
    }
}
