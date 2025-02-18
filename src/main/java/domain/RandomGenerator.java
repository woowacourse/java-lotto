package domain;

import java.util.*;

public class RandomGenerator {
    private RandomGenerator() {
    }

    public static List<Integer> generateUniqueRandomNumbers(int count, int start, int end) {
        Random random = new Random();

        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < count) {
            int number = random.nextInt(end - start + 1) + start;
            numbers.add(number);
        }
        return new ArrayList<>(numbers);
    }


}
