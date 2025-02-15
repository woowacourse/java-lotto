package util;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {

    public static List<Integer> generateNumbers(int start, int end, int count) {
        Set<Integer> uniqueNumbers = generateUniqueNumbers(start, end, count);
        return convertToSortedList(uniqueNumbers);
    }

    private static Set<Integer> generateUniqueNumbers(int start, int end, int count) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < count) {
            int number = random.nextInt(end - start + 1) + start;
            numbers.add(number);
        }

        return numbers;
    }

    private static List<Integer> convertToSortedList(Set<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .toList();
    }
}
