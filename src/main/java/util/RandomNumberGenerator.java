package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {

    public static List<Integer> getRandomNumbers(int minNumber, int maxNumber) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();
        while (numbers.size() != 6) {
            int number = random.nextInt(maxNumber - minNumber + 1) + minNumber;
            numbers.add(number);
        }
        return new ArrayList<>(numbers);
    }
}
