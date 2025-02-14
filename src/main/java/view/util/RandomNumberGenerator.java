package view.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static List<Integer> makeRandomNumber() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < LottoConstants.BALL_NUMBER_OF_ONE_LOTTO; i++) {
            randomNumbers.add(random.nextInt(LottoConstants.END_NUMBER_OF_LOTTO_RANGE) + 1);
        }
        return randomNumbers;
    }

}
