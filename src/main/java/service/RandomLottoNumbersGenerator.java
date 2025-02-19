package service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {

    private static Set<Integer> generateUniqueNumbers() {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < 6) {
            int number = random.nextInt(45) + 1;
            numbers.add(number);
        }

        return numbers;
    }

    @Override
    public List<Integer> generate() {
        Set<Integer> uniqueNumbers = generateUniqueNumbers();
        return uniqueNumbers.stream()
            .sorted()
            .toList();
    }
}
