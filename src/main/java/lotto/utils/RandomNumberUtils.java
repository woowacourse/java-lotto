package lotto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumberUtils {
    // 1~45 숫자 랜덤
    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(45) + 1;
    }

    public static List<Integer> generateRandomNumbers() {
        final Set<Integer> numberSet = new HashSet<>();

        while (numberSet.size() < 6) {
            final int randomNumber = generateRandomNumber();
            numberSet.add(randomNumber);
        }
        return new ArrayList<>(numberSet).stream().sorted().toList();
    }
}
