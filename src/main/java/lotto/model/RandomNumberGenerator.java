package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {

    public static List<Integer> generate() {
        Random random = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        for (int count = 0; count < Lotto.LOTTO_SIZE; count++) {
            randomNumbers.add(random.nextInt(Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER));
        }
        return randomNumbers;
    }

}
