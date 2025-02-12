package lotto.utils;

import java.util.Random;

public class NumberGenerator {

    public static int numberGenerator(int minValue, int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue - minValue - 1) + minValue;
    }
}
