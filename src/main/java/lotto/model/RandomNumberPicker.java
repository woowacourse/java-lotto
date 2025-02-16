package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumberPicker implements NumberPicker {

    private final Random random = new Random();

    @Override
    public Set<Integer> pickNumbersInRange(int start, int end, int count) {
        validateRange(start, end, count);
        return pickUniqueNumbers(start, end, count);
    }

    private Set<Integer> pickUniqueNumbers(int start, int end, int count) {
        List<Integer> uniqueNumbers = new ArrayList<>();
        while (uniqueNumbers.size() < count) {
            int number = generateRandomNumber(start, end);
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }
        return Set.copyOf(uniqueNumbers);
    }

    private int generateRandomNumber(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }
}
