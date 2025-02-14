package lotto.model;

import static lotto.model.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.model.lotto.LottoNumber.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {

    private RandomNumberGenerator() {
    }

    public static List<Integer> generate() {
        Random random = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        for (int count = 0; count < Lotto.LOTTO_SIZE; count++) {
            randomNumbers.add(random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
        return randomNumbers;
    }

}
