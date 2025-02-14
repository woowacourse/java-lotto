package model;

import static constant.LottoConstant.MAX_LOTTO_NUMBER;
import static constant.LottoConstant.MIN_LOTTO_NUMBER;

import java.util.concurrent.ThreadLocalRandom;

public class LottoNumberPicker {
    private final int UPPER_BOUND = 1;

    public LottoNumber pickRandomNumber() {
        return new LottoNumber(
                ThreadLocalRandom.current().nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + UPPER_BOUND)
        );
    }
}
