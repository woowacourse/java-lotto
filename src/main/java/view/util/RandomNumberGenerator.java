package view.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {
    private static final int BALL_NUMBER_OF_ONE_LOTTO = 6;
    private static final int END_NUMBER_OF_LOTTO_RANGE = 45;

    private static Random random = new Random();

    public static Set<Integer> makeRandomNumber() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while(lottoNumbers.size() < BALL_NUMBER_OF_ONE_LOTTO) {
            lottoNumbers.add(random.nextInt(END_NUMBER_OF_LOTTO_RANGE) + 1);
        }
        return lottoNumbers;
    }

}
