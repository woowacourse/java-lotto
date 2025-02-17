package model;

import static model.LottoNumber.MAXIMUM_LOTTO_NUMBER;

import java.util.Random;

public class LottoNumberGenerator implements NumberGenerator {
    @Override
    public int generate() {
        Random random = new Random();
        return random.nextInt(MAXIMUM_LOTTO_NUMBER) + 1;
    }
}
