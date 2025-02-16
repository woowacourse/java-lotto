package view.util;

import java.util.Random;

public class RandomNumberGenerator implements LottoNumberGenerator{
    private static final int START_NUMBER_OF_LOTTO_RANGE = 1;
    private static final int END_NUMBER_OF_LOTTO_RANGE = 45;

    private static Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(END_NUMBER_OF_LOTTO_RANGE) + START_NUMBER_OF_LOTTO_RANGE;
    }



}
