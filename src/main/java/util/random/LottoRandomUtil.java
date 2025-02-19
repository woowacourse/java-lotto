package util.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoRandomUtil implements RandomUtil {

    private final Random random = new Random();

    @Override
    public List<Integer> generateRandomNumbers(int minNumber, int maxNumber, int count) {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < count) {
            numbers.add(generateRandomNumber(minNumber, maxNumber));
        }

        return new ArrayList<>(numbers);
    }

    private int generateRandomNumber(int minNumber, int maxNumber) {
        return random.nextInt(maxNumber - minNumber + 1) + minNumber;
    }
}
