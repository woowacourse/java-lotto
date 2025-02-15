package lotto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class RandomNumberUtils {

    private static final Random random = new Random();

    private RandomNumberUtils() {
    }

    private static int generateRandomNumber(int maxNumber) {
        return random.nextInt(maxNumber) + 1;
    }

    public static List<Integer> generateRandomNumbers(int maxSize, int maxNumber) {
        final Set<Integer> numberSet = new HashSet<>();

        while (numberSet.size() < maxSize) {
            final int randomNumber = generateRandomNumber(maxNumber);
            numberSet.add(randomNumber);
        }
        return new ArrayList<>(numberSet).stream().sorted().toList();
    }
}
