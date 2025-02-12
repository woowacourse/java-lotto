package view.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static List<Integer> makeRandomNumber() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            randomNumbers.add(random.nextInt(45) + 1);
        }
        return randomNumbers;
    }

}
