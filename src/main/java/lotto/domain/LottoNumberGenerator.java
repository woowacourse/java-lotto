package lotto.domain;

import java.util.Random;

public class LottoNumberGenerator {

    private static final Random random = new Random();
    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int ONE = 1;

    public static int generatedLottoNumber() {
        return random.nextInt(MAX_LOTTO_NUMBER + ONE) + MIN_LOTTO_NUMBER;
    }
}
